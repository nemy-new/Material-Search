package com.nemynew.materialsearch.data

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.Normalizer
import java.util.Locale

data class ContactInfo(
    val id: String,
    val name: String,
    val phoneticName: String?,
    val phoneNumber: String?,
    val photoUri: Uri?
)

class ContactRepository(private val context: Context) {

    private var cachedContacts: List<ContactInfo> = emptyList()

    suspend fun loadContacts() {
        withContext(Dispatchers.IO) {
            val contacts = mutableListOf<ContactInfo>()
            val contentResolver = context.contentResolver
            val cursor: Cursor? = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                arrayOf(
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                    ContactsContract.CommonDataKinds.Phone.PHONETIC_NAME,
                    ContactsContract.CommonDataKinds.Phone.NUMBER,
                    ContactsContract.CommonDataKinds.Phone.PHOTO_URI
                ),
                null,
                null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
            )

            cursor?.use {
                val idIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)
                val nameIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                val phoneticIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHONETIC_NAME)
                val numberIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                val photoUriIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI)

                while (it.moveToNext()) {
                    val id = it.getString(idIndex)
                    val name = it.getString(nameIndex) ?: ""
                    val phoneticName = it.getString(phoneticIndex)
                    val number = it.getString(numberIndex)
                    val photoUriString = it.getString(photoUriIndex)
                    val photoUri = if (photoUriString != null) Uri.parse(photoUriString) else null
                    
                    // Avoid duplicates (same contact multiple numbers)
                    if (contacts.none { c -> c.id == id }) {
                         contacts.add(ContactInfo(id, name, phoneticName, number, photoUri))
                    }
                }
            }
            cachedContacts = contacts
        }
    }
    
    // Normalization logic shared with AppRepository (could be moved to util)
    private fun normalize(input: String): String {
        // Normalize Full-width/Half-width
        var text = Normalizer.normalize(input, Normalizer.Form.NFKC)
        // Convert Hiragana to Katakana for broader matching
        text = text.map { char ->
            if (char in '\u3041'..'\u3096') {
                (char.code + 0x60).toChar()
            } else {
                char
            }
        }.joinToString("")
        return text.lowercase(Locale.getDefault())
    }

    suspend fun searchContacts(query: String): List<ContactInfo> {
         if (cachedContacts.isEmpty()) {
            loadContacts()
        }
        
        if (query.isBlank()) {
            return emptyList() // Don't show contacts by default if query is blank
        }

        val normalizedQuery = normalize(query)

        return withContext(Dispatchers.Default) {
            cachedContacts.filter { contact ->
                val normalizedName = normalize(contact.name)
                val normalizedPhonetic = contact.phoneticName?.let { normalize(it) }

                if (normalizedName.contains(normalizedQuery, ignoreCase = true)) return@filter true
                
                if (normalizedPhonetic != null && normalizedPhonetic.contains(normalizedQuery, ignoreCase = true)) return@filter true
                
                // Also match phone number
                if (contact.phoneNumber?.contains(query) == true) return@filter true

                false
            }
        }
    }
}
