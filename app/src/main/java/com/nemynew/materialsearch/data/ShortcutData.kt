package com.nemynew.materialsearch.data

object ShortcutData {
    /**
     * Massive mapping for app shortcuts.
     * Covers: English abbreviations, Romaji inputs, Hiragana, Slang, and System Utilities.
     */
    val staticShortcuts: Map<String, List<String>> = mapOf(
        // ==========================================
        // 1. Social & Communication (SNS / 連絡)
        // ==========================================
        // LINE
        "line" to listOf("LINE", "ライン", "トーク"),
        "ln" to listOf("LINE", "ライン"),
        "rain" to listOf("LINE", "ライン"),
        "らいん" to listOf("LINE", "ライン"),
        
        // Twitter (X) - Both old and new naming conventions
        "twit" to listOf("Twitter", "X", "ツイッター", "エックス"),
        "twi" to listOf("Twitter", "X", "ツイッター"),
        "x" to listOf("X", "Twitter", "エックス", "ツイッター"),
        "bird" to listOf("Twitter", "X"), // Old slang
        "tweet" to listOf("Twitter", "X", "ツイート"),
        "post" to listOf("X", "Twitter", "ポスト"),
        "tsuitta" to listOf("Twitter", "ツイッター"),
        "ついったー" to listOf("Twitter", "X"),
        "えっくす" to listOf("X", "Twitter"),

        // Instagram
        "insta" to listOf("Instagram", "インスタグラム", "インスタ"),
        "ig" to listOf("Instagram", "インスタグラム"),
        "gram" to listOf("Instagram", "インスタグラム"),
        "insuta" to listOf("Instagram", "インスタ"),
        "いんすた" to listOf("Instagram", "インスタ"),

        // Facebook / Messenger
        "fb" to listOf("Facebook", "フェイスブック"),
        "face" to listOf("Facebook", "フェイスブック"),
        "fbook" to listOf("Facebook", "フェイスブック"),
        "msg" to listOf("Messenger", "Messages", "メッセージ", "SMS"),
        "mes" to listOf("Messenger", "Messages", "メッセージ"),
        "sms" to listOf("Messages", "SMS", "ショートメール"),
        "messe" to listOf("Messenger", "Messages", "メッセージ"),
        "めっせーじ" to listOf("Messenger", "Messages", "SMS"),

        // TikTok / Short Video
        "tik" to listOf("TikTok", "ティックトック"),
        "tok" to listOf("TikTok", "ティックトック"),
        "tt" to listOf("TikTok", "ティックトック"),
        "teikku" to listOf("TikTok", "ティックトック"),
        "てぃっく" to listOf("TikTok", "ティックトック"),

        // Discord / Chat
        "dc" to listOf("Discord", "ディスコード"),
        "dis" to listOf("Discord", "ディスコード"),
        "disco" to listOf("Discord", "ディスコード"),
        "chat" to listOf("Discord", "LINE", "Slack", "チャット"),
        "deisuko" to listOf("Discord", "ディスコード"),
        "でぃすこ" to listOf("Discord", "ディスコード"),

        // Threads / Bluesky
        "th" to listOf("Threads", "スレッズ"),
        "thr" to listOf("Threads", "スレッズ"),
        "bs" to listOf("Bluesky", "ブルースカイ"),
        "sky" to listOf("Bluesky", "Skype", "Sky: Children of the Light", "スカイプ"),

        // Slack / Business
        "slack" to listOf("Slack", "スラック", "仕事", "チャット"),
        "suraku" to listOf("Slack", "スラック"),
        "すらっく" to listOf("Slack", "スラック"),
        "shigoto" to listOf("Slack", "Teams", "仕事"),
        "しごと" to listOf("Slack", "Teams", "仕事"),

        // Phone / Contacts
        "tel" to listOf("Phone", "Dialer", "電話", "通話"),
        "ph" to listOf("Phone", "電話"),
        "call" to listOf("Phone", "電話"),
        "denwa" to listOf("Phone", "電話"),
        "でんわ" to listOf("Phone", "電話"),
        "con" to listOf("Contacts", "Phonebook", "連絡先", "電話帳"),
        "addr" to listOf("Contacts", "アドレス帳"),
        "renraku" to listOf("Contacts", "連絡先"),
        "れんらく" to listOf("Contacts", "連絡先"),

        // Skpye
        "skype" to listOf("Skype", "スカイプ"),
        "sukaipu" to listOf("Skype", "スカイプ"),
        "すかいぷ" to listOf("Skype", "スカイプ"),

        // ==========================================
        // 2. Google Ecosystem & Productivity (仕事 / 効率化)
        // ==========================================
        // Search / Browser
        "g" to listOf("Google", "Chrome", "Search", "グーグル"),
        "goog" to listOf("Google", "Search", "グーグル"),
        "sch" to listOf("Google", "Search", "Chrome", "検索"),
        "kensaku" to listOf("Google", "Search", "検索"),
        "web" to listOf("Chrome", "Browser", "Edge", "Firefox", "ブラウザ"),
        "net" to listOf("Chrome", "Browser", "インターネット"),
        "www" to listOf("Chrome", "Browser"),
        "ch" to listOf("Chrome", "クローム"),
        "chrom" to listOf("Chrome", "クローム"),
        "kuro" to listOf("Chrome", "クローム"),
        "くろーむ" to listOf("Chrome", "クローム"),

        // Mail
        "mail" to listOf("Gmail", "Email", "Outlook", "メール"),
        "gm" to listOf("Gmail", "ジーメール"),
        "me-ru" to listOf("Gmail", "Email", "メール"),
        "めーる" to listOf("Gmail", "Email", "メール"),

        // Drive / Cloud
        "drive" to listOf("Drive", "Google Drive", "ドライブ"),
        "dr" to listOf("Drive", "ドライブ"),
        "box" to listOf("Drive", "Dropbox", "OneDrive", "ボックス"),
        "cloud" to listOf("Drive", "OneDrive", "クラウド"),
        "doraibu" to listOf("Drive", "ドライブ"),
        "どらいぶ" to listOf("Drive", "ドライブ"),

        // Office Suite
        "doc" to listOf("Docs", "Word", "ドキュメント", "ワード"),
        "sheet" to listOf("Sheets", "Excel", "スプレッドシート", "エクセル"),
        "xls" to listOf("Sheets", "Excel", "表計算"),
        "slide" to listOf("Slides", "PowerPoint", "スライド", "パワポ"),
        "ppt" to listOf("Slides", "PowerPoint", "プレゼン"),

        // Keep / Notes
        "note" to listOf("Keep Notes", "Notes", "OneNote", "メモ", "ノート", "備忘録"),
        "memo" to listOf("Keep Notes", "Notes", "メモ"),
        "keep" to listOf("Keep Notes", "キープ"),
        "notepad" to listOf("Keep Notes", "Notes", "メモ帳"),
        "ki-pu" to listOf("Keep Notes", "キープ"),
        "めも" to listOf("Keep Notes", "メモ"),
        "のーと" to listOf("Notes", "ノート"),

        // Calendar / Tasks
        "cal" to listOf("Calendar", "Google Calendar", "カレンダー"),
        "date" to listOf("Calendar", "日付"),
        "plan" to listOf("Calendar", "Tasks", "ToDo", "予定"),
        "task" to listOf("Tasks", "ToDo", "タスク", "リマインダー"),
        "todo" to listOf("Tasks", "Keep Notes", "ToDo"),
        "karenda" to listOf("Calendar", "カレンダー"),
        "かれんだー" to listOf("Calendar", "カレンダー"),
        "yotei" to listOf("Calendar", "予定"),

        // Translate / Lens
        "trans" to listOf("Translate", "DeepL", "翻訳"),
        "honyaku" to listOf("Translate", "DeepL", "翻訳"),
        "ほんやく" to listOf("Translate", "翻訳"),
        "lens" to listOf("Google Lens", "レンズ"),
        "cam search" to listOf("Google Lens", "レンズ"),

        // ==========================================
        // 3. Entertainment (動画 / 音楽 / ゲーム)
        // ==========================================
        // YouTube
        "yt" to listOf("YouTube", "ユーチューブ"),
        "you" to listOf("YouTube", "ユーチューブ"),
        "tube" to listOf("YouTube", "ユーチューブ"),
        "yu-tyu" to listOf("YouTube", "ユーチューブ"),
        "ゆ" to listOf("YouTube", "ユーチューブ"), // Shortest unique
        "よ" to listOf("YouTube", "YouTube Music", "ユーチューブ"),
        "よう" to listOf("YouTube", "YouTube Music", "ユーチューブ"),
        "ようつべ" to listOf("YouTube", "YouTube Music", "ユーチューブ"),
        "ゆうちゅーぶ" to listOf("YouTube", "ユーチューブ"),
        "douga" to listOf("YouTube", "Netflix", "動画"),
        "どうが" to listOf("YouTube", "Netflix", "動画"),

        // Music
        "music" to listOf("YouTube Music", "Spotify", "Apple Music", "Amazon Music", "音楽", "ミュージック"),
        "mus" to listOf("YouTube Music", "Spotify", "音楽"),
        "spot" to listOf("Spotify", "スポティファイ"),
        "spo" to listOf("Spotify", "スポティファイ"),
        "supo" to listOf("Spotify", "スポティファイ"),
        "ongaku" to listOf("YouTube Music", "Spotify", "音楽"),
        "おんがく" to listOf("YouTube Music", "Spotify", "音楽"),
        "ようみゅー" to listOf("YouTube Music", "ようみゅ"),
        "radi" to listOf("radiko", "Radio", "ラジオ", "ラジコ"),
        "raji" to listOf("radiko", "ラジオ"),

        // Streaming Services
        "nflx" to listOf("Netflix", "ネットフリックス", "ネトフリ"),
        "nf" to listOf("Netflix", "ネトフリ"),
        "netofuri" to listOf("Netflix", "ネトフリ"),
        "ねとふり" to listOf("Netflix", "ネトフリ"),
        "hulu" to listOf("Hulu", "フールー"),
        "hu-ru-" to listOf("Hulu", "フールー"),
        "ふーるー" to listOf("Hulu", "フールー"),
        "prime" to listOf("Prime Video", "Amazon Prime", "プライムビデオ", "アマプラ"),
        "amapura" to listOf("Prime Video", "アマプラ"),
        "あまぷら" to listOf("Prime Video", "アマプラ"),
        "abema" to listOf("ABEMA", "アベマ"),
        "abe" to listOf("ABEMA", "アベマ"),
        "あべま" to listOf("ABEMA", "アベマ"),
        "tver" to listOf("TVer", "ティーバー"),
        "tv" to listOf("TVer", "Television", "テレビ"),
        "ti-ba-" to listOf("TVer", "ティーバー"),
        "てぃーばー" to listOf("TVer", "ティーバー"),
        "nico" to listOf("Niconico", "ニコニコ", "ニコ動"),
        "niko" to listOf("Niconico", "ニコニコ"),
        "にこにこ" to listOf("Niconico", "ニコニコ"),
        "にこどう" to listOf("Niconico", "ニコ動"),
        "dmm" to listOf("DMM", "DMM TV"),
        "dazn" to listOf("DAZN", "ダゾーン"),
        "disney" to listOf("Disney+", "ディズニープラス", "ディズニー"),
        "dizuni" to listOf("Disney+", "ディズニー"),
        "でぃずにー" to listOf("Disney+", "ディズニー"),
        "twitch" to listOf("Twitch", "ツイッチ"),
        "tsuitchi" to listOf("Twitch", "ツイッチ"),
        "ついっち" to listOf("Twitch", "ツイッチ"),
        "unext" to listOf("U-NEXT", "ユーネクスト"),
        "yu-nekusu" to listOf("U-NEXT", "ユーネクスト"),
        "ゆーねくすと" to listOf("U-NEXT", "ユーネクスト"),

        // Games
        "game" to listOf("Play Games", "Games", "ゲーム"),
        "play" to listOf("Play Store", "Play Games", "プレイ"),
        "poke" to listOf("Pokémon GO", "Pokemon", "ポケモン"),
        "pokego" to listOf("Pokémon GO", "ポケGO"),
        "ぽけもん" to listOf("Pokémon GO", "ポケモン"),
        "uma" to listOf("Uma Musume", "ウマ娘"),
        "umamusume" to listOf("Uma Musume", "ウマ娘"),
        "うまむすめ" to listOf("Uma Musume", "ウマ娘"),
        "うま" to listOf("Uma Musume", "ウマ娘"),
        "monst" to listOf("Monster Strike", "モンスト"),
        "pazu" to listOf("Puzzle & Dragons", "パズドラ"),
        "ぱずどら" to listOf("Puzzle & Dragons", "パズドラ"),
        "genshin" to listOf("Genshin Impact", "原神", "げんしん"),
        "gen" to listOf("Genshin Impact", "原神"),
        "げんしん" to listOf("Genshin Impact", "原神"),
        "minecraft" to listOf("Minecraft", "マインクラフト", "マイクラ"),
        "maikura" to listOf("Minecraft", "マイクラ"),
        "まいくら" to listOf("Minecraft", "マイクラ"),
        "apex" to listOf("Apex Legends", "エーペックスレジェンズ", "エペ"),
        "えぺ" to listOf("Apex Legends", "エペ"),
        "monhan" to listOf("Monster Hunter", "モンハン"),
        "もんはん" to listOf("Monster Hunter", "モンハン"),
        "fgo" to listOf("Fate/Grand Order", "FGO", "フェイト"),
        "feito" to listOf("Fate/Grand Order", "フェイト"),
        "ふぇいと" to listOf("Fate/Grand Order", "フェイト"),
        "buruaka" to listOf("Blue Archive", "ブルアカ"),
        "ぶるあか" to listOf("Blue Archive", "ブルアカ"),
        "stare" to listOf("Honkai: Star Rail", "スターレイル", "スタレ"),
        "sutare" to listOf("Honkai: Star Rail", "スタレ"),
        "すたれ" to listOf("Honkai: Star Rail", "スタレ"),
        "nikke" to listOf("NIKKE", "ニケ"),
        "にけ" to listOf("NIKKE", "ニケ"),
        "dq" to listOf("Dragon Quest", "DQ", "ドラクエ"),
        "dorakue" to listOf("Dragon Quest", "ドラクエ"),
        "どらくえ" to listOf("Dragon Quest", "ドラクエ"),
        "ff" to listOf("Final Fantasy", "FF", "ファイナルファンタジー"),

        // ==========================================
        // 4. Shopping & Payment (生活 / 決済)
        // ==========================================
        // Shopping
        "ama" to listOf("Amazon", "Shopping", "アマゾン"),
        "amz" to listOf("Amazon", "アマゾン"),
        "azon" to listOf("Amazon", "アマゾン"),
        "amazon" to listOf("Amazon", "アマゾン"),
        "あまぞん" to listOf("Amazon", "アマゾン"),
        "raku" to listOf("Rakuten", "Rakuten Ichiba", "楽天", "楽天市場"),
        "r" to listOf("Rakuten", "楽天"),
        "rakuten" to listOf("Rakuten", "楽天"),
        "らくてん" to listOf("Rakuten", "楽天"),
        "shop" to listOf("Amazon", "Rakuten", "Shopping", "買い物", "ショップ", "通販"),
        "kai" to listOf("Amazon", "Rakuten", "買い物"),
        "yodo" to listOf("Yodobashi", "ヨドバシ"),
        "bic" to listOf("Bic Camera", "ビックカメラ"),
        "tsuhan" to listOf("Amazon", "Rakuten", "通販"),
        "つうはん" to listOf("Amazon", "Rakuten", "通販"),

        // Flea Market
        "mer" to listOf("Mercari", "メルカリ"),
        "merc" to listOf("Mercari", "メルカリ"),
        "kari" to listOf("Mercari", "メルカリ"),
        "merukari" to listOf("Mercari", "メルカリ"),
        "めるかり" to listOf("Mercari", "メルカリ"),
        "paypayflea" to listOf("PayPay Flea Market", "PayPayフリマ", "ペイフリ"),
        "peifuri" to listOf("PayPay Flea Market", "ペイフリ"),

        // Payment (Code)
        "pay" to listOf("PayPay", "PayPayフリマ", "Google Pay", "Rakuten Pay", "d Payment", "ペイ", "支払い", "決済"),
        "pp" to listOf("PayPay", "ペイペイ"),
        "peipei" to listOf("PayPay", "ペイペイ"),
        "ぺい" to listOf("PayPay", "ペイ"),
        "dpay" to listOf("d Payment", "d払い"),
        "dbarai" to listOf("d Payment", "d払い"),
        "aupay" to listOf("au PAY", "auペイ"),
        "rpay" to listOf("Rakuten Pay", "楽天ペイ"),
        "らくてんぺい" to listOf("Rakuten Pay", "楽天ペイ"),

        // Payment (NFC/Wallet)
        "wal" to listOf("Google Wallet", "Wallet", "Osaifu-Keitai", "ウォレット"),
        "saifu" to listOf("Google Wallet", "Osaifu-Keitai", "おサイフケータイ", "財布"),
        "さいふ" to listOf("Google Wallet", "Osaifu-Keitai", "財布"),
        "sui" to listOf("Suica", "Mobile Suica", "スイカ"),
        "suica" to listOf("Suica", "スイカ"),
        "すいか" to listOf("Suica", "スイカ"),
        "pas" to listOf("PASMO", "Mobile PASMO", "パスモ"),
        "pasmo" to listOf("PASMO", "パスモ"),
        "ico" to listOf("ICOCA", "Mobile ICOCA", "イコカ"),
        "nanaco" to listOf("nanaco", "ナナコ"),
        "waon" to listOf("WAON", "ワオン"),

        // Points
        "point" to listOf("d Point", "Rakuten Point", "Ponta", "V Point", "ポイント"),
        "ponta" to listOf("Ponta", "ポンタ"),
        "dpoint" to listOf("d Point", "dポイント"),
        "vpoint" to listOf("V Point", "T Point", "Vポイント", "Tポイント"),

        // Yahoo Shopping
        "yahoosho" to listOf("Yahoo! Shopping", "ヤフーショッピング", "ヤフショ"),
        "yafushou" to listOf("Yahoo! Shopping", "ヤフショ"),
        "やふしょ" to listOf("Yahoo! Shopping", "ヤフショ"),

        // ==========================================
        // 5. Food & Lifestyle (飲食 / お店)
        // ==========================================
        // Delivery
        "uber" to listOf("Uber Eats", "Uber", "ウーバー"),
        "eats" to listOf("Uber Eats", "イーツ"),
        "u-ba-" to listOf("Uber Eats", "ウーバー"),
        "うーばー" to listOf("Uber Eats", "ウーバー"),
        "demae" to listOf("Demaecan", "出前館"),
        "kan" to listOf("Demaecan", "出前館"),
        "de" to listOf("Uber Eats", "Demaecan", "出前"),
        "deri" to listOf("Uber Eats", "Demae-can", "デリバリー"),
        "でりばりー" to listOf("Uber Eats", "Demae-can", "デリバリー"),

        // Chains
        "mac" to listOf("McDonald's", "マクドナルド", "マック", "マクド"),
        "mcd" to listOf("McDonald's", "マクドナルド"),
        "makudo" to listOf("McDonald's", "マクド"),
        "まくど" to listOf("McDonald's", "マクド"),
        "makku" to listOf("McDonald's", "マック"),
        "まっく" to listOf("McDonald's", "マック"),
        "star" to listOf("Starbucks", "スタバ", "スターバックス"),
        "stb" to listOf("Starbucks", "スタバ"),
        "suta" to listOf("Starbucks", "スタバ"),
        "sutaba" to listOf("Starbucks", "スタバ"),
        "すたば" to listOf("Starbucks", "スタバ"),
        "seven" to listOf("7-Eleven", "Seven-Eleven", "セブン"),
        "law" to listOf("Lawson", "ローソン"),
        "fami" to listOf("FamilyMart", "ファミマ"),
        "matsu" to listOf("Matsuya", "松屋"),
        "suki" to listOf("Sukiya", "すき家"),
        "kura" to listOf("Kura Sushi", "くら寿司", "Kurashiru", "クラシル"),
        "くら" to listOf("Kura Sushi", "くら寿司"),
        "sushiro" to listOf("Sushiro", "スシロー"),
        "すしろー" to listOf("Sushiro", "スシロー"),

        // Fashion
        "uniqlo" to listOf("UNIQLO", "ユニクロ"),
        "yuni" to listOf("UNIQLO", "ユニクロ"),
        "ゆにくろ" to listOf("UNIQLO", "ユニクロ"),
        "gu" to listOf("GU", "ジーユー"),
        "zi-yu-" to listOf("GU", "ジーユー"),
        "じーゆー" to listOf("GU", "ジーユー"),

        // Recipe / Life
        "cook" to listOf("Cookpad", "Kurashiru", "Delish Kitchen", "クックパッド", "レシピ"),
        "reci" to listOf("Kurashiru", "Cookpad", "レシピ"),
        "daiso" to listOf("Daiso", "ダイソー", "100均"),
        "だいそー" to listOf("Daiso", "ダイソー"),
        "hyakkin" to listOf("Daiso", "Seria", "Can Do", "100均"),
        "ひゃっきん" to listOf("Daiso", "Seria", "Can Do", "100均"),
        "nitori" to listOf("Nitori", "ニトリ"),
        "muji" to listOf("MUJI", "無印良品", "無印"),
        "donki" to listOf("Don Quijote", "ドンキ", "ドン・キホーテ"),
        "どんき" to listOf("Don Quijote", "ドンキ"),

        // ==========================================
        // 6. Travel & Transport (移動 / 地図)
        // ==========================================
        // Maps
        "map" to listOf("Maps", "Google Maps", "マップ", "地図"),
        "nav" to listOf("Maps", "Google Maps", "ナビ"),
        "mappu" to listOf("Maps", "マップ"),
        "まっぷ" to listOf("Maps", "マップ"),
        "chizu" to listOf("Maps", "地図"),
        "ちず" to listOf("Maps", "地図"),

        // Train / Transit
        "train" to listOf("NAVITIME", "Jorudan", "JapanTransit", "乗換案内", "電車"),
        "jor" to listOf("Jorudan", "ジョルダン", "乗換"),
        "navi" to listOf("NAVITIME", "Gurunavi", "ナビタイム", "ナビ"),
        "norikae" to listOf("NAVITIME", "Jorudan", "乗換案内"),
        "のりかえ" to listOf("NAVITIME", "Jorudan", "乗換案内"),
        "eki" to listOf("NAVITIME", "Station", "駅"),
        "ex" to listOf("EX App", "Smart EX", "EXアプリ", "新幹線"),
        "densha" to listOf("NAVITIME", "Jorudan", "電車"),
        "でんしゃ" to listOf("NAVITIME", "Jorudan", "電車"),
        "jikoku" to listOf("NAVITIME", "Jorudan", "時刻表"),
        "じこく" to listOf("NAVITIME", "Jorudan", "時刻表"),

        // Taxi
        "taxi" to listOf("GO", "S.RIDE", "Uber", "タクシー", "配車"),
        "takushi-" to listOf("Taxi", "タクシー"),
        "たくしー" to listOf("Taxi", "タクシー"),
        "go" to listOf("GO", "ゴー", "タクシー"),
        "sride" to listOf("S.RIDE", "エスライド"),

        // Airline
        "ana" to listOf("ANA", "All Nippon Airways", "全日空"),
        "jal" to listOf("JAL", "Japan Airlines", "日本航空"),
        "air" to listOf("ANA", "JAL", "Flight", "航空"),
        "flight" to listOf("ANA", "JAL", "Flight", "フライト"),

        // Travel
        "travel" to listOf("Jalan", "Rakuten Travel", "旅行", "ホテル"),
        "ryoko" to listOf("Travel", "旅行"),
        "りょこう" to listOf("Travel", "旅行"),
        "jalan" to listOf("Jalan", "じゃらん"),
        "じゃらん" to listOf("Jalan", "じゃらん"),

        // ==========================================
        // 7. Finance & Utility (金融 / 行政 / ツール)
        // ==========================================
        // Banking
        "bank" to listOf("Bank", "Mitsubishi", "SMBC", "Mizuho", "銀行"),
        "bk" to listOf("Bank", "銀行"),
        "ginkou" to listOf("Bank", "銀行"),
        "ぎんこう" to listOf("Bank", "銀行"),
        "ufj" to listOf("Mitsubishi UFJ", "三菱UFJ"),
        "smbc" to listOf("SMBC", "三井住友"),
        "mizu" to listOf("Mizuho", "みずほ"),
        "yu" to listOf("Japan Post Bank", "Yucho", "ゆうちょ"),
        "ゆうちょ" to listOf("Japan Post Bank", "Yucho", "ゆうちょ"),
        "sbi" to listOf("SBI", "Sumishin SBI"),

        // Gov / ID
        "myna" to listOf("Myna Portal", "マイナポータル", "マイナンバー"),
        "my" to listOf("Myna Portal", "マイナポータル"),
        "gov" to listOf("Myna Portal", "e-Tax"),
        "etax" to listOf("e-Tax", "確定申告"),

        // Weather
        "wea" to listOf("Weather", "WeatherNews", "Tenki.jp", "天気"),
        "ten" to listOf("Weather", "Tenki.jp", "天気"),
        "てんき" to listOf("Weather", "天気"),
        "ame" to listOf("Weather", "Tokyo Amesh", "雨雲"),
        "tenki" to listOf("Weather", "天気"),
        "yoho" to listOf("Weather", "予報"),
        "よほう" to listOf("Weather", "予報"),

        // AI & Assistant
        "ai" to listOf("Gemini", "ChatGPT", "Notion AI", "Copilot", "Claude", "AI", "人工知能"),
        "gemini" to listOf("Gemini", "ジェミニ"),
        "jemini" to listOf("Gemini", "ジェミニ"),
        "じぇみに" to listOf("Gemini", "ジェミニ"),
        "chatgpt" to listOf("ChatGPT", "チャットジーピーティー"),
        "chat" to listOf("ChatGPT", "チャット"),
        "ちゃっと" to listOf("ChatGPT", "チャット"),
        "gpt" to listOf("ChatGPT", "ジーピーティー"),
        "deepl" to listOf("DeepL", "ディープエル"),
        "でぃーぷ" to listOf("DeepL", "ディープエル"),
        "claude" to listOf("Claude", "クロード"),
        "perplexity" to listOf("Perplexity", "パープレキシティ"),

        // Design & Creative
        "adobe" to listOf("Adobe", "Photoshop", "Lightroom", "Acrobat", "アドビ"),
        "adobi" to listOf("Adobe", "アドビ"),
        "あどび" to listOf("Adobe", "アドビ"),
        "ps" to listOf("Photoshop", "フォトショップ"),
        "lr" to listOf("Lightroom", "ライトルーム"),
        "pixiv" to listOf("pixiv", "ピクシブ"),
        "ぴくしぶ" to listOf("pixiv", "ピクシブ"),

        // System Tools
        "set" to listOf("Settings", "設定"),
        "conf" to listOf("Settings", "Config", "設定"),
        "せ" to listOf("Settings", "設定"),
        "settei" to listOf("Settings", "設定"),
        "せってい" to listOf("Settings", "設定"),
        "wifi" to listOf("Settings", "Wi-Fi", "Network"),
        "bt" to listOf("Settings", "Bluetooth"),
        "batt" to listOf("Settings", "Battery", "電池"),
        "sound" to listOf("Settings", "Sound", "音"),
        "disp" to listOf("Settings", "Display", "画面"),
        "dev" to listOf("Settings", "Developer Options", "開発者"),
        
        // Basic Utilities
        "dentaku" to listOf("Calculator", "電卓"),
        "でんたく" to listOf("Calculator", "電卓"),
        "cam" to listOf("Camera", "カメラ"),
        "kamera" to listOf("Camera", "カメラ"),
        "かめら" to listOf("Camera", "カメラ"),
        "pic" to listOf("Photos", "Gallery", "Google Photos", "写真", "画像", "フォト"),
        "photo" to listOf("Photos", "Google Photos", "Gallery", "写真", "画像", "フォト"),
        "ph" to listOf("Photos", "Google Photos", "Gallery", "写真"),
        "shashin" to listOf("Photos", "Google Photos", "Gallery", "写真"),
        "しゃしん" to listOf("Photos", "Google Photos", "Gallery", "写真"),
        "foto" to listOf("Photos", "Google Photos", "フォト"),
        "ふぉと" to listOf("Photos", "Google Photos", "フォト"),
        "album" to listOf("Gallery", "Photos", "アルバム"),
        "arubamu" to listOf("Gallery", "Photos", "アルバム"),
        "あるばむ" to listOf("Gallery", "Photos", "アルバム"),
        "gal" to listOf("Gallery", "Photos", "ギャラリー"),
        "gallery" to listOf("Gallery", "Photos", "ギャラリー"),
        "gyara" to listOf("Gallery", "ギャラリー"),
        "ぎゃらりー" to listOf("Gallery", "ギャラリー"),
        "gazou" to listOf("Photos", "Gallery", "画像"),
        "がぞう" to listOf("Photos", "Gallery", "画像"),
        "satsuei" to listOf("Camera", "Photos", "撮影"),
        "さつえい" to listOf("Camera", "Photos", "撮影"),
        "clock" to listOf("Clock", "Alarm", "時計"),
        "tokei" to listOf("Clock", "時計"),
        "とけい" to listOf("Clock", "時計"),
        "file" to listOf("Files", "File Manager", "ファイル"),
        "huairu" to listOf("Files", "ファイル"),
        "ふぁいる" to listOf("Files", "ファイル"),
        "rec" to listOf("Recorder", "Voice Recorder", "録音", "ボイスメモ"),

        // Store
        "store" to listOf("Play Store", "Google Play", "ストア"),
        "sutoa" to listOf("Play Store", "ストア"),
        "すとあ" to listOf("Play Store", "ストア"),
        "dl" to listOf("Play Store", "ダウンロード"),

        // ==========================================
        // 8. AI & Advanced (最新AI / 高度なツール)
        // ==========================================
        "claude" to listOf("Claude", "Anthropic", "クロード"),
        "claud" to listOf("Claude", "クロード"),
        "くろーど" to listOf("Claude", "クロード"),
        "midjourney" to listOf("Midjourney", "ミッドジャーニー"),
        "mj" to listOf("Midjourney", "ミッドジャーニー"),
        "perp" to listOf("Perplexity", "パープレキシティ"),
        "perplexity" to listOf("Perplexity", "パープレキシティ"),
        "sd" to listOf("Stable Diffusion", "ステイブルディフュージョン"),
        "stable" to listOf("Stable Diffusion", "画像生成"),

        // ==========================================
        // 9. Shopping & Point (ショップ追加)
        // ==========================================
        "rakum" to listOf("Rakuma", "ラクマ"),
        "rakuma" to listOf("Rakuma", "ラクマ"),
        "らくま" to listOf("Rakuma", "ラクマ"),
        "zozo" to listOf("ZOZOTOWN", "ゾゾタウン", "ゾゾ"),
        "zozotown" to listOf("ZOZOTOWN", "ゾゾタウン"),
        "ぞぞ" to listOf("ZOZOTOWN", "ゾゾ"),
        "yafu" to listOf("Yahoo! Auctions", "ヤフオク"),
        "yafuo" to listOf("Yahoo! Auctions", "ヤフオク"),
        "やふおく" to listOf("Yahoo! Auctions", "ヤフオク"),
        "magi" to listOf("Magi", "マギ"),

        // ==========================================
        // 10. More Games (ゲーム追加)
        // ==========================================
        "legends" to listOf("Dragon Ball Legends", "レジェンズ"),
        "legend" to listOf("Dragon Ball Legends", "レジェンズ"),
        "reje" to listOf("Legends", "レジェンズ"),
        "れじぇ" to listOf("Legends", "レジェンズ"),
        "dokkan" to listOf("Dokkan Battle", "ドッカンバトル", "ドッカン"),
        "dokkan" to listOf("Dokkan Battle", "ドッカン"),
        "どっかん" to listOf("Dokkan Battle", "ドッカン"),
        "ps" to listOf("PS App", "PlayStation App", "プレステ"),
        "preste" to listOf("PS App", "プレステ"),
        "ぷれすて" to listOf("PS App", "プレステ"),
        "xbox" to listOf("Xbox", "エックスボックス"),
        "switch" to listOf("Nintendo Switch Online", "スイッチ"),
        "suitchi" to listOf("Switch", "スイッチ"),
        "すいっち" to listOf("Switch", "スイッチ"),
        "pogo" to listOf("Pokémon GO", "ポケGO"),
        "pon" to listOf("Pokémon Sleep", "ポケスリ"),
        "pokesuri" to listOf("Pokémon Sleep", "ポケスリ"),
        "ぽけすり" to listOf("Pokémon Sleep", "ポケスリ"),

        // ==========================================
        // 11. Utilities & Health (ツール / 健康)
        // ==========================================
        "qr" to listOf("QR Code", "QR Reader", "Scanner", "QRコード"),
        "vpn" to listOf("VPN", "Proxy", "セキュア"),
        "ad" to listOf("AdBlock", "AdGuard"),
        "step" to listOf("Health", "Step Counter", "万歩計", "ヘルスケア"),
        "ho" to listOf("Health", "万歩計"),
        "manpo" to listOf("Health", "万歩計"),
        "まんぽ" to listOf("Health", "万歩計"),
        "sleep" to listOf("Health", "Sleep", "Pokémon Sleep", "睡眠"),
        "suimin" to listOf("Sleep", "睡眠"),
        "すいみん" to listOf("Sleep", "睡眠"),

        // ==========================================
        // 12. Dev & Tech (開発 / 技術 / ギーク)
        // ==========================================
        "git" to listOf("GitHub", "Git", "ギットハブ"),
        "github" to listOf("GitHub", "ギットハブ"),
        "hub" to listOf("GitHub", "ハブ"),
        "gitlab" to listOf("GitLab", "ギットラボ"),
        "vs" to listOf("VS Code", "Visual Studio", "コード"),
        "code" to listOf("VS Code", "Visual Studio", "コード"),
        "term" to listOf("Termux", "Terminal", "ターミナル"),
        "cmd" to listOf("Termux", "Command", "コマンド"),
        "shell" to listOf("Termux", "Shell", "シェル"),
        "authenticator" to listOf("Authenticator", "Google Authenticator", "Microsoft Authenticator", "認証"),
        "auth" to listOf("Authenticator", "認証"),
        "aws" to listOf("AWS Console", "Amazon Web Services"),
        "gcp" to listOf("Google Cloud Console", "GCP"),
        "azure" to listOf("Azure", "アジュール"),
        "rdp" to listOf("Remote Desktop", "Microsoft Remote Desktop", "リモート"),
        "speed" to listOf("Speedtest", "スピードテスト", "速度", "速度測定"),
        "sokudo" to listOf("Speedtest", "速度"),

        // ==========================================
        // 13. Manga & Reading (マンガ / 電子書籍)
        // ==========================================
        "jump" to listOf("Shonen Jump+", "Jump+", "ジャンプ+", "ジャンプ"),
        "janpu" to listOf("Shonen Jump+", "ジャンプ"),
        "j+" to listOf("Shonen Jump+", "ジャンプ+"),
        "maga" to listOf("Magazine Pocket", "マガポケ", "マガジン"),
        "magapoke" to listOf("Magazine Pocket", "マガポケ"),
        "sunday" to listOf("Sunday Webry", "サンデーうぇぶり", "サンデー"),
        "piccoma" to listOf("Piccoma", "ピッコマ"),
        "pikkoma" to listOf("Piccoma", "ピッコマ"),
        "linemanga" to listOf("LINE Manga", "LINEマンガ"),
        "mecha" to listOf("Mecha Comic", "めちゃコミック", "めちゃコミ"),
        "cmoa" to listOf("Comic Cmoa", "コミックシーモア", "シーモア"),
        "semoa" to listOf("Comic Cmoa", "シーモア"),
        "renta" to listOf("Renta!", "レンタ"),

        // ==========================================
        // 14. Dating & Matching (マッチング / 婚活)
        // ==========================================
        "tinder" to listOf("Tinder", "ティンダー"),
        "teinda" to listOf("Tinder", "ティンダー"),
        "pairs" to listOf("Pairs", "ペアーズ"),
        "pea" to listOf("Pairs", "ペアーズ"),
        "taple" to listOf("Taple", "タップル"),
        "omiai" to listOf("Omiai", "オミアイ"),
        "with" to listOf("with", "ウィズ"),
        "match" to listOf("Pairs", "Tinder", "Matching", "マッチング"),

        // ==========================================
        // 15. Live Streaming (ライブ配信)
        // ==========================================
        "17" to listOf("17LIVE", "イチナナ"),
        "ichinana" to listOf("17LIVE", "イチナナ"),
        "poco" to listOf("Pococha", "ポコチャ"),
        "pokotya" to listOf("Pococha", "ポコチャ"),
        "showroom" to listOf("SHOWROOM", "ショールーム"),
        "mirrativ" to listOf("Mirrativ", "ミラティブ"),
        "mira" to listOf("Mirrativ", "ミラティブ"),
        "twitcast" to listOf("TwitCasting", "ツイキャス"),
        "cas" to listOf("TwitCasting", "ツイキャス"),
        "kyasu" to listOf("TwitCasting", "キャス"),
        "spoon" to listOf("Spoon", "スプーン"),
        "iriam" to listOf("IRIAM", "イリアム"),

        // ==========================================
        // 16. Productivity & Knowledge (知識 / 管理)
        // ==========================================
        "notion" to listOf("Notion", "ノーション"),
        "nosyon" to listOf("Notion", "ノーション"),
        "obsidian" to listOf("Obsidian", "オブシディアン"),
        "ever" to listOf("Evernote", "エバーノート"),
        "trello" to listOf("Trello", "トレロ"),
        "jira" to listOf("Jira", "ジラ"),
        "zoom" to listOf("Zoom", "ズーム"),
        "zu-mu" to listOf("Zoom", "ズーム"),
        "meet" to listOf("Google Meet", "Meet", "ミート"),
        "mi-to" to listOf("Google Meet", "Meet", "ミート"),
        "teams" to listOf("Microsoft Teams", "Teams", "チームズ"),
        "chi-muzu" to listOf("Microsoft Teams", "チームズ"),
        "asana" to listOf("Asana", "アサナ"),
        "gas" to listOf("Gas Station", "ガソリン", "ENEOS"), 

        // ==========================================
        // 17. Education & Study (学習 / 勉強)
        // ==========================================
        "duo" to listOf("Duolingo", "デュオリンゴ"),
        "duolingo" to listOf("Duolingo", "デュオリンゴ"),
        "eigo" to listOf("Duolingo", "English", "英語"),
        "study" to listOf("Studyplus", "スタディプラス", "スタプラ", "勉強"),
        "stapu" to listOf("Studyplus", "スタプラ"),
        "mikan" to listOf("Mikan", "英単語"),
        "photomath" to listOf("Photomath"),

        // ==========================================
        // 18. Food & Reservations (グルメ / 予約)
        // ==========================================
        "tabe" to listOf("Tabelog", "食べログ"),
        "log" to listOf("Tabelog", "食べログ"),
        "retty" to listOf("Retty", "レッティ"),
        "hot" to listOf("Hot Pepper", "ホットペッパー"),
        "pepper" to listOf("Hot Pepper", "ホットペッパー"),
        "coke" to listOf("Coke ON", "コークオン"),
        "ko-ku" to listOf("Coke ON", "コークオン"),

        // ==========================================
        // 19. Ticket & Event (チケット / イベント)
        // ==========================================
        "ticket" to listOf("Ticket Pia", "e+", "Lawson Ticket", "チケット"),
        "pia" to listOf("Ticket Pia", "チケットぴあ", "ぴあ"),
        "eplus" to listOf("e+", "イープラス"),
        "i-pura" to listOf("e+", "イープラス"),
        "lo-chike" to listOf("Lawson Ticket", "ローチケ"),
        "movie" to listOf("TOHO Cinemas", "AEON CINEMA", "映画"),
        "eiga" to listOf("TOHO Cinemas", "映画"),

        // ==========================================
        // 20. Car & Bike (車 / バイク / シェア)
        // ==========================================
        "luup" to listOf("LUUP", "ループ", "キックボード"),
        "ru-pu" to listOf("LUUP", "ループ"),
        "share" to listOf("Times Car", "Anyca", "シェアカー", "カーシェア"),
        "times" to listOf("Times Car", "タイムズ"),
        "taimuzu" to listOf("Times Car", "タイムズ"),
        "navitime" to listOf("NAVITIME", "Drive Supporter", "カーナビ"),
        "carnavi" to listOf("NAVITIME", "Google Maps", "カーナビ"),

        // ==========================================
        // 21. Real Estate & Moving (住まい)
        // ==========================================
        "suumo" to listOf("SUUMO", "スーモ"),
        "su-mo" to listOf("SUUMO", "スーモ"),
        "hom" to listOf("LIFULL HOME'S", "ホームズ"),
        "ho-muzu" to listOf("LIFULL HOME'S", "ホームズ"),
        "athome" to listOf("at home", "アットホーム"),

        // ==========================================
        // 22. Browser Games & DMM (その他ゲーム)
        // ==========================================
        "kancolle" to listOf("KanColle", "艦これ"),
        "kan" to listOf("KanColle", "艦これ"),
        "touken" to listOf("Touken Ranbu", "刀剣乱舞"),
        "toulove" to listOf("Touken Ranbu", "とうらぶ"),
        "priconne" to listOf("Princess Connect!", "プリコネ"),
        "purikone" to listOf("Princess Connect!", "プリコネ"),
        "idol" to listOf("Idolmaster", "Imas", "アイマス", "デレステ", "ミリシタ"),
        "imas" to listOf("Idolmaster", "アイマス"),
        "deresute" to listOf("Starlight Stage", "デレステ"),
        "pjsekai" to listOf("Project Sekai", "プロセカ"),
        "puroseka" to listOf("Project Sekai", "プロセカ"),
        "sekai" to listOf("Project Sekai", "セカイ"),
        "cod" to listOf("Call of Duty", "CoD", "コールオブデューティ"),
        "pubg" to listOf("PUBG MOBILE", "パブジー"),
        "kouya" to listOf("Knives Out", "荒野行動"),
        "koudou" to listOf("Knives Out", "行動"),

        // ==========================================
        // 23. Utilities II (細かいツール補完)
        // ==========================================
        "nfc" to listOf("NFC Tools", "Osaifu-Keitai", "NFC"),
        "remote" to listOf("Remote", "SwitchBot", "Nature Remo", "Remote Desktop", "TeamViewer", "AnyDesk", "リモコン", "リモート"),
        "remocon" to listOf("Remote", "SwitchBot", "リモコン"),
        "bot" to listOf("SwitchBot", "ボット"),
        "nature" to listOf("Nature Remo", "ネイチャーリモ"),
        "measure" to listOf("Measure", "計測", "メジャー"),
        "scale" to listOf("Scale", "体重計", "定規"),
        "compass" to listOf("Compass", "コンパス", "方位"),
        "houi" to listOf("Compass", "方位"),
        "mirror" to listOf("Mirror", "鏡", "ミラー"),
        "kagami" to listOf("Mirror", "鏡"),
        "radiko" to listOf("radiko", "ラジコ"), 
        "nhk" to listOf("NHK Plus", "NHK News", "NHK"),
        "plus" to listOf("NHK Plus", "プラス"),

        // ==========================================
        // 24. Disaster & Safety (防災 / 緊急)
        // ==========================================
        "nerv" to listOf("Gehirn Web Services", "NERV", "防災", "地震"),
        "jishin" to listOf("NERV", "Yahoo! Bosai", "地震"),
        "bosai" to listOf("Yahoo! Bosai", "NERV", "防災速報"),
        "yurekuru" to listOf("Yurekuru Call", "ゆれくる"),
        "171" to listOf("Disaster Message Board", "災害用伝言板"),
        "110" to listOf("Emergency Call", "110番", "警察"),
        "119" to listOf("Emergency Call", "119番", "消防", "救急"),
        "kokoco" to listOf("COCOA", "接触確認"),

        // ==========================================
        // 25. Medical & Health (医療 / 薬 / 生理)
        // ==========================================
        "med" to listOf("EPARK", "Medicine Notebook", "お薬手帳"),
        "kusuri" to listOf("Medicine Notebook", "EPARK", "お薬手帳"),
        "okusuri" to listOf("EPARK", "お薬手帳"),
        "luna" to listOf("Luna Luna", "ルナルナ"),
        "seiri" to listOf("Luna Luna", "Clue", "生理管理"),
        "epark" to listOf("EPARK", "予約"),
        "shika" to listOf("Dentist", "EPARK", "歯科"),
        "haisha" to listOf("Dentist", "EPARK", "歯医者"),

        // ==========================================
        // 26. Job & Career (就活 / バイト / 転職)
        // ==========================================
        "baito" to listOf("Baitoru", "Townwork", "Mynavi Baito", "バイト"),
        "town" to listOf("Townwork", "タウンワーク"),
        "rikunabi" to listOf("Rikunabi", "リクナビ"),
        "mynavi" to listOf("Mynavi", "マイナビ"),
        "tenshoku" to listOf("LinkedIn", "Wantedly", "転職"),
        "indeed" to listOf("Indeed", "インディード"),
        "wanted" to listOf("Wantedly", "ウォンテッドリー"),
        "en" to listOf("En Tenshoku", "エン転職"),
        "doda" to listOf("doda", "デューダ"),
        "shukatsu" to listOf("Rikunabi", "Mynavi", "就活"),

        // ==========================================
        // 27. Investment & Crypto (投資 / 仮想通貨)
        // ==========================================
        "stock" to listOf("SBI Securities", "Rakuten Securities", "株", "証券"),
        "kabu" to listOf("SBI Securities", "Rakuten Securities", "株"),
        "fx" to listOf("DMM FX", "GMO Click", "FX"),
        "coin" to listOf("Coincheck", "bitFlyer", "Binance", "仮想通貨", "暗号資産"),
        "bit" to listOf("bitFlyer", "ビットフライヤー"),
        "check" to listOf("Coincheck", "コインチェック"),
        "meta" to listOf("MetaMask", "メタマスク"),
        "chart" to listOf("TradingView", "チャート"),
        "view" to listOf("TradingView", "トレーディングビュー"),
        "nisa" to listOf("NISA", "Tsumitate NISA", "ニーサ"),

        // ==========================================
        // 28. Music Production & Creative (制作 / DAW)
        // ==========================================
        "fl" to listOf("FL Studio Mobile", "FL Studio", "DAW"),
        "daw" to listOf("FL Studio", "Cubase", "GarageBand"),
        "garage" to listOf("GarageBand", "ガレバン"),
        "band" to listOf("BandLab", "バンドラボ"),
        "sound" to listOf("SoundCloud", "サウンドクラウド"),
        "sc" to listOf("SoundCloud", "サンクラ"),
        "tuner" to listOf("Tuner", "GuitarTuner", "チューナー"),
        "metro" to listOf("Metronome", "メトロノーム"),
        "bpm" to listOf("Metronome", "BPM"),
        "vj" to listOf("Resolume", "VirtualDJ", "VJ"), 

        // ==========================================
        // 29. Smart Home & IoT (スマートホーム)
        // ==========================================
        "alexa" to listOf("Amazon Alexa", "アレクサ"),
        "echo" to listOf("Amazon Alexa", "Echo"),
        "hue" to listOf("Philips Hue", "ヒュー"),
        "remo" to listOf("Nature Remo", "リモ"),
        "switchbot" to listOf("SwitchBot", "スイッチボット"),
        "iot" to listOf("SwitchBot", "Nature Remo", "Smart Life"),

        // ==========================================
        // 30. Creator Economy & Otaku (推し活 / 同人)
        // ==========================================
        "skeb" to listOf("Skeb", "スケブ"),
        "fantia" to listOf("Fantia", "ファンティア"),
        "fanbox" to listOf("pixivFANBOX", "ファンボックス"),
        "booth" to listOf("BOOTH", "ブース"),
        "melon" to listOf("Melonbooks", "メロンブックス"),
        "tora" to listOf("Toranoana", "とらのあな"),
        "comike" to listOf("Comiket Web Catalog", "コミケ"),
        "animate" to listOf("Animate", "アニメイト"),
        "suruga" to listOf("Surugaya", "駿河屋"),
        "mandarake" to listOf("Mandarake", "まんだらけ"),

        // ==========================================
        // 31. Network & Advanced Tools (高度なツール)
        // ==========================================
        "nord" to listOf("NordVPN", "ノード"),
        "tor" to listOf("Tor Browser", "Tor"),
        "onion" to listOf("Tor Browser", "Tor"),
        "geek" to listOf("Geekbench", "Benchmark", "ベンチマーク"),
        "cpu" to listOf("CPU-Z", "CPU Monitor"),
        "gpu" to listOf("GPU-Z", "3DMark"),
        "desk" to listOf("Remote Desktop", "AnyDesk", "リモート"),
        "ssh" to listOf("Termux", "ConnectBot", "SSH"),
        "ftp" to listOf("FileZilla", "FTP"),

        // ==========================================
        // 32. Browser Alternatives (ブラウザ)
        // ==========================================
        "brave" to listOf("Brave Browser", "ブレイブ"),
        "vivaldi" to listOf("Vivaldi", "ビバルディ"),
        "opera" to listOf("Opera", "オペラ"),
        "duck" to listOf("DuckDuckGo", "ダックダックゴー"),
        "fox" to listOf("Firefox", "ファイアフォックス"),
        "edge" to listOf("Edge", "エッジ"),

        // ==========================================
        // 33. Career & Office (オフィス / 文書)
        // ==========================================
        "pdf" to listOf("Adobe Acrobat", "PDF Reader", "PDF"),
        "scan" to listOf("Google Lens", "Office Lens", "Scanner", "スキャン"),
        "print" to listOf("Print", "Net Print", "コンビニプリント", "印刷"),
        "netprint" to listOf("netprint", "Network Print", "ネップリ"),
        "fax" to listOf("Mobile Fax", "ファックス"),
        "meishi" to listOf("Eight", "Wantedly People", "名刺"),
        "eight" to listOf("Eight", "エイト"),

        // ==========================================
        // 34. Education & Kids (教育 / 子供)
        // ==========================================
        "class" to listOf("Google Classroom", "Classroom", "クラスルーム"),
        "moodle" to listOf("Moodle", "ムードル"),
        "benesse" to listOf("Shinkenzemi", "Benesse", "進研ゼミ"),
        "kumon" to listOf("Kumon", "公文"),
        "sapuri" to listOf("Study Sapuri", "スタサプ"),

        // ==========================================
        // 35. Fitness & Outdoors (運動 / アウトドア)
        // ==========================================
        "run" to listOf("Strava", "Nike Run Club", "Runtastic", "ランニング"),
        "strava" to listOf("Strava", "ストラバ"),
        "yamap" to listOf("YAMAP", "ヤマップ", "登山"),
        "yama" to listOf("YAMAP", "YamaReco", "山"),
        "camp" to listOf("Camp", "キャンプ"),
        "fit" to listOf("Google Fit", "Fitbit", "フィットネス"),
        "ring" to listOf("Ring Fit", "リングフィット"), 

        // ==========================================
        // 36. Japanese Slang & Typing Errors (入力補完)
        // ==========================================
        "w" to listOf("草", "笑", "Warau"), 
        "ggr" to listOf("Google", "ググる"),
        "hima" to listOf("YouTube", "Netflix", "TikTok", "暇つぶし"),
        "tsurai" to listOf("Twitter", "X", "愚痴"), 
        "nemui" to listOf("Alarm", "Sleep", "睡眠"),
        "hara" to listOf("Uber Eats", "Demaecan", "McDonald's", "腹減った"),
        "meshi" to listOf("Uber Eats", "Tabelog", "飯"),

        // ==========================================
        // 37. Additional Shortcuts (その他)
        // ==========================================
        "karaoke" to listOf("DAM", "JOYSOUND", "カラオケ"),
        "dam" to listOf("DAM", "デンモク"),
        "joy" to listOf("JOYSOUND", "キョクナビ"),
        "un" to listOf("Unext", "ユーネクスト"), 
        "fanza" to listOf("DMM", "FANZA", "ファンザ"), 
        "post" to listOf("Japan Post", "郵便局", "ポスト", "ゆうびん"),
        "kuroneko" to listOf("Yamato Transport", "クロネコヤマト", "宅急便"),
        "yamato" to listOf("Yamato Transport", "ヤマト"),
        "sagawa" to listOf("Sagawa Express", "佐川急便"),
        "jaf" to listOf("JAF", "ロードサービス"),

        // ==========================================
        // 38. Poi-katsu & Flyers (ポイ活 / チラシ)
        // ==========================================
        "poi" to listOf("Shufoo!", "Tokubai", "Powl", "Trigger", "ポイ活"),
        "poikatsu" to listOf("Powl", "Trigger", "ポイ活"),
        "chirashi" to listOf("Shufoo!", "Tokubai", "チラシ"),
        "shufoo" to listOf("Shufoo!", "シュフー"),
        "tokubai" to listOf("Tokubai", "トクバイ"),

        // ==========================================
        // 39. Beauty & Salon (美容 / サロン / ネイル)
        // ==========================================
        "beauty" to listOf("Hot Pepper Beauty", "ホットペッパービューティー"),
        "biyo" to listOf("Hot Pepper Beauty", "美容院"),
        "nail" to listOf("Hot Pepper Beauty", "Nailbook", "ネイル"),
        "lip" to listOf("LIPS", "リップス", "コスメ"),
        "cosme" to listOf("@cosme", "LIPS", "コスメ"),

        // ==========================================
        // 40. Home Electronics & Infrastructure (家電 / インフラ)
        // ==========================================
        "denki" to listOf("TEPCO", "Denki", "電気"),
        "gas" to listOf("Gas", "Tokyo Gas", "ガス"),
        "water" to listOf("Water", "Suido", "水道"),
        "sharp" to listOf("COCORO HOME", "シャープ"),
        "panasonic" to listOf("Panasonic", "パナソニック"),
        "sony" to listOf("Sony", "Music Center", "ソニー"),
        "bravia" to listOf("Sony", "Bravia", "ブラビア"),

        // ==========================================
        // 41. More Niche Games & Culture (マニアック・流行)
        // ==========================================
        "mahjong" to listOf("Mahjong Soul", "Jansoul", "雀魂", "麻雀"),
        "jan" to listOf("Mahjong Soul", "雀魂"),
        "soul" to listOf("Mahjong Soul", "雀魂"),
        "vampire" to listOf("Vampire Survivors", "ヴァンサバ"),
        "vansaba" to listOf("Vampire Survivors", "ヴァンサバ"),
        "zzz" to listOf("Zenless Zone Zero", "ゼンレスゾーンゼロ", "ゼンゼロ"),
        "zenzero" to listOf("Zenless Zone Zero", "ゼンゼロ"),
        "shaka" to listOf("Shikanoko", "しかのこのこのここしたんたん"), // 流行・ネタ

        // ==========================================
        // 42. Mobile Hardware & Safety (ハードウェア・安全)
        // ==========================================
        "light" to listOf("Flashlight", "ライト", "懐中電灯"),
        "kaichu" to listOf("Flashlight", "懐中電灯"),
        "らいと" to listOf("Flashlight", "ライト"),
        "light" to listOf("Flashlight", "ライト", "懐中電灯"),
        "kaichu" to listOf("Flashlight", "懐中電灯"),
        "らいと" to listOf("Flashlight", "ライト"),
        "storage" to listOf("Storage", "Files", "ストレージ"),

        // ==========================================
        // 43. Ultimate Banking Expansion (銀行・金融完全網羅)
        // ==========================================
        // Mega Banks
        "mufg" to listOf("Mitsubishi UFJ", "MUFG", "三菱UFJ銀行", "三菱", "UFJ", "銀行"),
        "mutsu" to listOf("Mitsubishi UFJ", "三菱"),
        "smbc" to listOf("Sumitomo Mitsui", "SMBC", "三井住友銀行", "三井", "住友"),
        "mizuho" to listOf("Mizuho", "みずほ銀行", "みずほ"),
        "resona" to listOf("Resona", "りそな銀行", "りそな"),
        "risona" to listOf("Resona", "りそな"),
        "saitama" to listOf("Saitama Resona", "埼玉りそな"),
        
        // Post Bank
        "yucho" to listOf("Japan Post Bank", "Yucho", "ゆうちょ銀行", "ゆうちょ", "郵便局"),
        "yu" to listOf("Yucho", "ゆうちょ"),
        "postal" to listOf("Japan Post Bank", "郵便局"),
        
        // Net Banks
        "sbi" to listOf("SBI Shinsei", "Sumishin SBI", "SBI", "住信"),
        "shinsei" to listOf("SBI Shinsei", "新生銀行"),
        "rakugin" to listOf("Rakuten Bank", "楽天銀行", "楽銀"),
        "paypaygin" to listOf("PayPay Bank", "PayPay銀行", "ジャパンネット"),
        "sonybank" to listOf("Sony Bank", "ソニー銀行"),
        "aeonbank" to listOf("Aeon Bank", "イオン銀行"),
        "aujibun" to listOf("au Jibun Bank", "auじぶん銀行", "じぶん銀行"),
        "jibun" to listOf("au Jibun Bank", "じぶん銀行"),
        
        // Regional Banks (Major)
        "joyo" to listOf("Joyo Bank", "常陽銀行", "常陽"),
        "juroku" to listOf("Juroku Bank", "十六銀行", "十六"),
        "ogaki" to listOf("Ogaki Kyoritsu", "OKB", "大垣共立銀行", "大垣"),
        "okb" to listOf("Ogaki Kyoritsu", "大垣共立"),
        "shishi" to listOf("Shishi", "第四北越"),
        "hoku" to listOf("Hokuriku", "北陸銀行", "北越"),
        "shigin" to listOf("Shizuoka Bank", "静岡銀行", "静銀"),
        "chigin" to listOf("Chiba Bank", "千葉銀行", "千葉銀"),
        "fugin" to listOf("Fukuoka Bank", "福岡銀行", "福銀"),
        "hokugin" to listOf("Hokkaido Bank", "北海道銀行", "道銀"),
        "hirogin" to listOf("Hiroshima Bank", "広島銀行", "広銀"),
        "kyogin" to listOf("Kyoto Bank", "京都銀行", "京銀"),
        "gun" to listOf("Gunma Bank", "群馬銀行", "群銀"),
        "ashigaga" to listOf("Ashikaga Bank", "足利銀行"),
        "hyakugo" to listOf("Hyakugo Bank", "百五銀行"),
        "nanto" to listOf("Nanto Bank", "南都銀行"),
        "iyo" to listOf("Iyo Bank", "伊予銀行"),
        "higo" to listOf("Higo Bank", "肥後銀行"),
        "kagoshima" to listOf("Kagoshima Bank", "鹿児島銀行"),
        
        // ==========================================
        // 44. Ultimate Gov & Admin (行政・公共サービス)
        // ==========================================
        "tax" to listOf("e-Tax", "確定申告", "税金", "納税"),
        "zeikin" to listOf("e-Tax", "確定申告", "税金"),
        "etax" to listOf("e-Tax", "確定申告"),
        "nenkin" to listOf("Nenkin Net", "年金定期便", "日本年金機構", "年金"),
        "hoken" to listOf("Health Insurance", "Mynaportal", "健康保険", "保険証"),
        "kokuho" to listOf("National Health Insurance", "国保"),
        "myna" to listOf("Mynaportal", "マイナポイント", "マイナンバー"),
        "maina" to listOf("Mynaportal", "マイナンバー"),
        "jumin" to listOf("City Hall", "住民票", "役所"),
        "shiyakusho" to listOf("City Hall", "市役所"),
        "kuyakusho" to listOf("Ward Office", "区役所"),
        "passport" to listOf("Passport", "外務省", "パスポート"),
        "shaken" to listOf("Car Inspection", "車検"),
        "kyufu" to listOf("Mynaportal", "給付金"),
        
        // ==========================================
        // 45. Adult & Nightlife Master (成人向け・夜の街)
        // ==========================================
        "adult" to listOf("FANZA", "DMM", "Twitter", "X", "大人", "18禁"),
        "ero" to listOf("FANZA", "DMM", "Twitter", "X", "エロ"),
        "18" to listOf("FANZA", "DMM", "18禁"),
        "av" to listOf("FANZA", "DMM", "AV"),
        "fanza" to listOf("FANZA", "DMM", "ファンザ"),
        "dmm" to listOf("DMM", "FANZA", "DMM TV"),
        "fuzoku" to listOf("Nightlife", "風俗", "デリヘル", "ソープ"),
        "deri" to listOf("Nightlife", "デリヘル"),
        "soap" to listOf("Nightlife", "ソープ"),
        "kyaba" to listOf("Cabaret", "キャバクラ"),
        "host" to listOf("Host Club", "ホスト"),
        "deai" to listOf("Tinder", "Pairs", "Taple", "出会い", "マッチング"),
        "matching" to listOf("Tinder", "Pairs", "Taple", "マッチング"),
        "tinder" to listOf("Tinder", "ティンダー"),
        "pairs" to listOf("Pairs", "ペアーズ"),
        "taple" to listOf("Taple", "タップル"),
        "with" to listOf("with", "ウィズ"),
        "omiai" to listOf("Omiai", "オミアイ"),
        "meromero" to listOf("MeroMero", "メロメロ"),
        
        // ==========================================
        // 46. Life & Emotions Slang (日常・感情・スラング)
        // ==========================================
        "hara" to listOf("Uber Eats", "Demaecan", "McDonald's", "腹減った", "飯"),
        "meshi" to listOf("Uber Eats", "Tabelog", "飯", "店"),
        "gohan" to listOf("Uber Eats", "Tabelog", "ご飯"),
        "nemui" to listOf("Alarm", "Clock", "Sleep", "眠い"),
        "zzz" to listOf("Sleep", "Alarm", "寝る"),
        "nero" to listOf("Sleep", "寝ろ"),
        "hima" to listOf("YouTube", "Netflix", "TikTok", "Shorts", "暇", "暇つぶし"),
        "boring" to listOf("YouTube", "Game", "退屈"),
        "tsukare" to listOf("Health", "Music", "疲れた", "休憩", "マッサージ"),
        "shindo" to listOf("Health", "Music", "しんどい"),
        "ira" to listOf("Music", "ASMR", "Game", "イライラ"),
        "angry" to listOf("Music", "ASMR", "怒り"),
        "sad" to listOf("Music", "Netflix", "悲しい"),
        "happy" to listOf("Music", "TikTok", "幸せ"),
        "kanashi" to listOf("Music", "悲しい"),
        "tanoshi" to listOf("Game", "TikTok", "楽しい"),
        "yaruki" to listOf("Music", "Task", "Notes", "やる気"),
        "work" to listOf("Slack", "Teams", "Gmail", "仕事"),
        "shigoto" to listOf("Slack", "Teams", "仕事"),
        "yametai" to listOf("転職", "Indeed", "辞めたい"),
        "tenshoku" to listOf("Indeed", "Rikunabi", "転職"),
        
        // ==========================================
        // 47. Advanced Shopping & Flea (通販・フリマ・激安)
        // ==========================================
        "qoo" to listOf("Qoo10", "キューテン"),
        "qoo10" to listOf("Qoo10", "キューテン"),
        "shein" to listOf("SHEIN", "シーイン"),
        "temu" to listOf("Temu", "テム"),
        "ali" to listOf("AliExpress", "アリエク"),
        "aliexpress" to listOf("AliExpress", "アリエク"),
        "shop" to listOf("Amazon", "Rakuten", "Yahoo", "Shopping", "買い物"),
        "kaimono" to listOf("Amazon", "Rakuten", "買い物"),
        "point" to listOf("d Point", "Ponta", "V Point", "楽天ポイント", "ポイ活"),
        "poi" to listOf("Powl", "Trigger", "ポイ活"),
        "furu" to listOf("Furunaa", "Satofuru", "ふるさと納税"),
        "sato" to listOf("Satofuru", "さとふる"),
        
        // ==========================================
        // 48. Ultimate Utility Expansion (ツール・ハード・極)
        // ==========================================
        "batt" to listOf("Battery", "Settings", "電池"),
        "denchi" to listOf("Battery", "電池"),
        "cpu" to listOf("CPU Monitor", "System Info", "CPU"),
        "gpu" to listOf("GPU Monitor", "Benchmark"),
        "ram" to listOf("RAM Cleaner", "System Info", "メモリ"),
        "memory" to listOf("Memory", "Files", "メモリ"),
        "bench" to listOf("Geekbench", "Antutu", "3DMark", "ベンチマーク"),
        "speed" to listOf("Speedtest", "Fast.com", "速度"),
        "wifi" to listOf("Settings", "Wi-Fi"),
        "bt" to listOf("Settings", "Bluetooth"),
        "nfc" to listOf("NFC Tools", "Osaifu-Keitai", "NFC"),
        "sim" to listOf("Settings", "SIM", "キャリア"),
        "root" to listOf("Magisk", "Termux", "Root"),
        "adb" to listOf("Settings", "Developer Options", "ADB"),
        "debug" to listOf("Settings", "Developer Options", "デバッグ"),
        
        // ==========================================
        // 49. Niche Hobby Expansion (趣味・マニアック)
        // ==========================================
        "tsuri" to listOf("Fishing", "釣り"),
        "fish" to listOf("Fishing", "釣り"),
        "horse" to listOf("JRA", "Keiba", "競馬"),
        "keiba" to listOf("JRA", "競馬"),
        "pachi" to listOf("Pachinko", "Pachislot", "パチンコ", "パチスロ"),
        "slot" to listOf("Pachislot", "スロット"),
        "shogi" to listOf("Shogi", "将棋"),
        "go" to listOf("Go", "囲碁"),
        "mah" to listOf("Mahjong", "麻雀"),
        "book" to listOf("Kindle", "Kobo", "Honto", "本"),
        "yomu" to listOf("Kindle", "Manga", "読む"),
        
        // ==========================================
        // 50. Global Brand Overdrive (グローバルブランド)
        // ==========================================
        "apple" to listOf("Apple Music", "Apple Store", "アップル"),
        "google" to listOf("Google", "Chrome", "Search", "グーグル"),
        "ms" to listOf("Microsoft", "Outlook", "Teams", "マイクロソフト"),
        "microsoft" to listOf("Microsoft", "マイクロソフト"),
        "meta" to listOf("Facebook", "Instagram", "WhatsApp", "メタ"),
        "amazon" to listOf("Amazon", "Prime", "アマゾン"),
        "sony" to listOf("Sony", "PlayStation", "Music Center", "ソニー"),
        "samsung" to listOf("Samsung", "Gallery", "Galaxy Store", "サムスン"),
        "galaxy" to listOf("Galaxy Store", "Galaxy Wearable", "ギャラクシー"),
        
        // ==========================================
        // 51. Ultimate Misspellings & Typo (究極・入力補完)
        // ==========================================
        "googk" to listOf("Google"),
        "facrb" to listOf("Facebook"),
        "insts" to listOf("Instagram"),
        "twitt" to listOf("Twitter", "X"),
        "yitube" to listOf("YouTube"),
        "liine" to listOf("LINE"),
        "amason" to listOf("Amazon"),
        "nrtflix" to listOf("Netflix"),
        "spotiffy" to listOf("Spotify"),
        "whata" to listOf("WhatsApp"),
        "kik" to listOf("TikTok"),
        "teikku" to listOf("TikTok"),
        "yu-tyu" to listOf("YouTube"),

        // ==========================================
        // 38. Chat & Messenger (チャットアプリ特化)
        // ==========================================
        
        // --- Telegram (テレグラム) ---
        "tele" to listOf("Telegram", "テレグラム"),
        "tg" to listOf("Telegram", "テレグラム"),
        "telega" to listOf("Telegram", "テレグラム"),
        "telegram" to listOf("Telegram", "テレグラム"),
        "tereguramu" to listOf("Telegram", "テレグラム"),
        "tere" to listOf("Telegram", "テレグラム"),
        "てれぐらむ" to listOf("Telegram", "テレグラム"),
        "てれ" to listOf("Telegram", "テレグラム"),

        // --- WhatsApp (ワッツアップ) ---
        "what" to listOf("WhatsApp", "ワッツアップ"),
        "wa" to listOf("WhatsApp", "ワッツアップ"),
        "whatapp" to listOf("WhatsApp", "ワッツアップ"),
        "whatsup" to listOf("WhatsApp", "ワッツアップ"),
        "watts" to listOf("WhatsApp", "ワッツアップ"),
        "wattsu" to listOf("WhatsApp", "ワッツアップ"),
        "ottsu" to listOf("WhatsApp", "ワッツアップ"), // "オッツ"と聞こえる場合
        "わっつ" to listOf("WhatsApp", "ワッツアップ"),
        "わっつあっぷ" to listOf("WhatsApp", "ワッツアップ"),

        // --- Signal (シグナル) ---
        "sig" to listOf("Signal", "シグナル"),
        "signal" to listOf("Signal", "シグナル"),
        "shigunaru" to listOf("Signal", "シグナル"),
        "sigu" to listOf("Signal", "シグナル"),
        "しぐなる" to listOf("Signal", "シグナル"),
        "しぐ" to listOf("Signal", "シグナル"),

        // --- KakaoTalk (カカオトーク) ---
        "kakao" to listOf("KakaoTalk", "カカオトーク"),
        "kaka" to listOf("KakaoTalk", "カカオトーク"),
        "talk" to listOf("KakaoTalk", "Talk", "カカオ"),
        "kt" to listOf("KakaoTalk", "カカオ"),
        "kakaoto-ku" to listOf("KakaoTalk", "カカオトーク"),
        "かかお" to listOf("KakaoTalk", "カカオ"),
        "かかおとーく" to listOf("KakaoTalk", "カカオトーク"),
        "カカオ" to listOf("KakaoTalk", "カカオ"),

        // --- WeChat (ウィーチャット) ---
        "we" to listOf("WeChat", "ウィーチャット"),
        "wechat" to listOf("WeChat", "ウィーチャット"),
        "wc" to listOf("WeChat", "ウィーチャット"),
        "ui" to listOf("WeChat", "ウィーチャット"),
        "ui-chatto" to listOf("WeChat", "ウィーチャット"),
        "uichatto" to listOf("WeChat", "ウィーチャット"),
        "うぃー" to listOf("WeChat", "ウィーチャット"),
        "うぃーちゃっと" to listOf("WeChat", "ウィーチャット"),

        // --- Rakuten Viber (バイバー) ---
        "viber" to listOf("Rakuten Viber", "Viber", "バイバー"),
        "vi" to listOf("Viber", "バイバー"),
        "vaiba" to listOf("Viber", "バイバー"),
        "baiba" to listOf("Viber", "バイバー"),
        "ばいばー" to listOf("Viber", "バイバー"),

        // --- Skype (スカイプ) ---
        "sky" to listOf("Skype", "スカイプ"),
        "skype" to listOf("Skype", "スカイプ"),
        "sukai" to listOf("Skype", "スカイプ"),
        "sukaipu" to listOf("Skype", "スカイプ"),
        "すかいぷ" to listOf("Skype", "スカイプ"),
        "すかい" to listOf("Skype", "スカイプ"),

        // --- Kik (キック) ---
        "kik" to listOf("Kik", "キック"),
        "kikku" to listOf("Kik", "キック"),
        "ki" to listOf("Kik", "キック"), // 短すぎるが文脈次第
        "きっく" to listOf("Kik", "キック"),

        // --- Snapchat (スナップチャット) ---
        "snap" to listOf("Snapchat", "スナップチャット"),
        "sc" to listOf("Snapchat", "スナチャ"),
        "suna" to listOf("Snapchat", "スナチャ"),
        "sunacha" to listOf("Snapchat", "スナチャ"),
        "sunappu" to listOf("Snapchat", "スナップチャット"),
        "すな" to listOf("Snapchat", "スナチャ"),
        "すなちゃ" to listOf("Snapchat", "スナチャ"),
        "すなっぷ" to listOf("Snapchat", "スナップチャット"),

        // --- Session (セッション - 高匿名) ---
        "sess" to listOf("Session", "セッション"),
        "session" to listOf("Session", "セッション"),
        "sesu" to listOf("Session", "セッション"),
        "sesshon" to listOf("Session", "セッション"),
        "せっしょん" to listOf("Session", "セッション"),

        // --- Threema (スリーマ - 高セキュリティ) ---
        "threema" to listOf("Threema", "スリーマ"),
        "three" to listOf("Threema", "スリーマ"),
        "suri-ma" to listOf("Threema", "スリーマ"),
        "すりーま" to listOf("Threema", "スリーマ"),

        // --- Element / Matrix (エレメント - 分散型) ---
        "ele" to listOf("Element", "Matrix", "エレメント"),
        "element" to listOf("Element", "エレメント"),
        "matrix" to listOf("Element", "Matrix", "マトリックス"),
        "eremento" to listOf("Element", "エレメント"),
        "えれめんと" to listOf("Element", "エレメント"),

        // --- Wire (ワイヤー) ---
        "wire" to listOf("Wire", "ワイヤー"),
        "waiya" to listOf("Wire", "ワイヤー"),
        "わいやー" to listOf("Wire", "ワイヤー"),

        // --- Wickr (ウィッカー) ---
        "wickr" to listOf("Wickr", "Wickr Me", "ウィッカー"),
        "uikka" to listOf("Wickr", "ウィッカー"),
        "うぃっかー" to listOf("Wickr", "ウィッカー"),

        // --- Slack (スラック - 再掲・強化) ---
        "sl" to listOf("Slack", "スラック"),
        "slack" to listOf("Slack", "スラック"),
        "sura" to listOf("Slack", "スラック"),
        "surakku" to listOf("Slack", "スラック"),
        "すらっく" to listOf("Slack", "スラック"),
        "すら" to listOf("Slack", "スラック"),

        // --- Microsoft Teams (チームズ - 再掲・強化) ---
        "team" to listOf("Microsoft Teams", "Teams", "チームズ"),
        "teams" to listOf("Microsoft Teams", "Teams", "チームズ"),
        "tm" to listOf("Microsoft Teams", "Teams"),
        "ti-mu" to listOf("Teams", "チームズ"),
        "ti-muzu" to listOf("Teams", "チームズ"),
        "ちーむ" to listOf("Teams", "チームズ"),
        "ちーむず" to listOf("Teams", "チームズ"),

        // --- Discord (ディスコード - 再掲・強化) ---
        "dc" to listOf("Discord", "ディスコード"),
        "dis" to listOf("Discord", "ディスコード"),
        "disco" to listOf("Discord", "ディスコード"),
        "deisuko" to listOf("Discord", "ディスコード"),
        "でぃすこ" to listOf("Discord", "ディスコード"),
        "でぃす" to listOf("Discord", "ディスコード"),

        // --- Chatwork (チャットワーク) ---
        "cw" to listOf("Chatwork", "チャットワーク"),
        "chatwork" to listOf("Chatwork", "チャットワーク"),
        "chatto" to listOf("Chatwork", "ChatGPT", "チャット"), // 文脈による
        "chattowa-ku" to listOf("Chatwork", "チャットワーク"),
        "ちゃっとわーく" to listOf("Chatwork", "チャットワーク"),

        // --- Mattermost (マターモースト) ---
        "matter" to listOf("Mattermost", "マターモースト"),
        "matamo" to listOf("Mattermost", "マタモ"),
        "またー" to listOf("Mattermost", "マターモースト"),

        // --- Rocket.Chat (ロケットチャット) ---
        "rocket" to listOf("Rocket.Chat", "ロケットチャット"),
        "roketto" to listOf("Rocket.Chat", "ロケチャ"),
        "ろけっと" to listOf("Rocket.Chat", "ロケットチャット"),

        // --- Zalo (ザロ - ベトナム系) ---
        "zalo" to listOf("Zalo", "ザロ"),
        "zaro" to listOf("Zalo", "ザロ"),
        "ざろ" to listOf("Zalo", "ザロ"),

        // --- GroupMe (グループミー) ---
        "group" to listOf("GroupMe", "グループミー"),
        "guru-pu" to listOf("GroupMe", "グループミー"),

        // --- ICQ (アイシーキュー - レガシー) ---
        "icq" to listOf("ICQ", "アイシーキュー"),
        "aishi" to listOf("ICQ", "アイシーキュー"),

        // --- +Message (プラスメッセージ) ---
        "purasu" to listOf("+Message", "Plus Message", "プラスメッセージ"),
        "pumesse" to listOf("+Message", "プラメ"),
        "plume" to listOf("+Message", "プラメ"),
        "ぷらす" to listOf("+Message", "プラスメッセージ"),
        "ぷらめ" to listOf("+Message", "プラメ"),

        // ==========================================
        // 39. Shopping: Major EC (総合通販 / モール)
        // ==========================================
        // Amazon
        "ama" to listOf("Amazon", "Shopping", "アマゾン", "通販"),
        "amz" to listOf("Amazon", "アマゾン"),
        "azon" to listOf("Amazon", "アマゾン"),
        "amazon" to listOf("Amazon", "アマゾン"),
        "amazon" to listOf("Amazon", "アマゾン"),
        "あま" to listOf("Amazon", "アマゾン"),
        "あまぞん" to listOf("Amazon", "アマゾン"),

        // Rakuten
        "raku" to listOf("Rakuten", "Rakuten Ichiba", "楽天", "楽天市場"),
        "ichiba" to listOf("Rakuten Ichiba", "楽天市場", "市場"),
        "r" to listOf("Rakuten", "楽天"),
        "rakuten" to listOf("Rakuten", "楽天"),
        "rakutenichiba" to listOf("Rakuten Ichiba", "楽天市場"),
        "らくてん" to listOf("Rakuten", "楽天"),
        "いちば" to listOf("Rakuten Ichiba", "楽天市場"),

        // Yahoo! Shopping
        "yahoo" to listOf("Yahoo! Shopping", "Yahoo", "ヤフー"),
        "sho" to listOf("Yahoo! Shopping", "Shopping", "ショッピング"),
        "yahoosho" to listOf("Yahoo! Shopping", "ヤフショ"),
        "yafushou" to listOf("Yahoo! Shopping", "ヤフショ"),
        "yasu" to listOf("Yahoo! Shopping", "ヤフー"), // 安い？
        "やふー" to listOf("Yahoo! Shopping", "Yahoo", "ヤフー"),
        "やふしょ" to listOf("Yahoo! Shopping", "ヤフショ"),

        // Qoo10 (eBay Japan)
        "qoo" to listOf("Qoo10", "キューテン", "メガ割"),
        "qoo10" to listOf("Qoo10", "キューテン"),
        "kyu" to listOf("Qoo10", "キューテン"),
        "kyuten" to listOf("Qoo10", "キューテン"),
        "mega" to listOf("Qoo10", "メガ割"),
        "きゅーてん" to listOf("Qoo10", "キューテン"),
        "めがわり" to listOf("Qoo10", "メガ割"),

        // au PAY Market
        "aupaym" to listOf("au PAY Market", "au PAY マーケット"),
        "wowma" to listOf("au PAY Market", "Wowma", "ワウマ"), // 旧名
        "ma-ketto" to listOf("au PAY Market", "マーケット"),

        // ==========================================
        // 40. Shopping: Flea Market & Auction (フリマ / オークション)
        // ==========================================
        // Mercari
        "mer" to listOf("Mercari", "メルカリ"),
        "merc" to listOf("Mercari", "メルカリ"),
        "kari" to listOf("Mercari", "メルカリ"),
        "merukari" to listOf("Mercari", "メルカリ"),
        "merucari" to listOf("Mercari", "メルカリ"),
        "めるかり" to listOf("Mercari", "メルカリ"),
        "める" to listOf("Mercari", "メルカリ"),

        // Yahoo! Auctions
        "yafuo" to listOf("Yahoo! Auctions", "ヤフオク"),
        "oku" to listOf("Yahoo! Auctions", "Auction", "オークション"),
        "auc" to listOf("Yahoo! Auctions", "Auction", "オークション"),
        "yahuoku" to listOf("Yahoo! Auctions", "ヤフオク"),
        "やふおく" to listOf("Yahoo! Auctions", "ヤフオク"),
        "おく" to listOf("Yahoo! Auctions", "オークション"),

        // Rakuma (Rakuten Flea)
        "rakum" to listOf("Rakuma", "ラクマ"),
        "fril" to listOf("Rakuma", "フリル"), // 旧名
        "rakuma" to listOf("Rakuma", "ラクマ"),
        "furiru" to listOf("Rakuma", "フリル"),
        "らくま" to listOf("Rakuma", "ラクマ"),

        // PayPay Flea Market
        "payf" to listOf("PayPay Flea Market", "PayPayフリマ"),
        "peifuri" to listOf("PayPay Flea Market", "ペイフリ"),
        "furima" to listOf("PayPay Flea Market", "Mercari", "フリマ"),
        "ぺいふり" to listOf("PayPay Flea Market", "ペイフリ"),
        "ふりま" to listOf("PayPay Flea Market", "フリマ"),

        // SNKRDUNK (Sneakers)
        "snkr" to listOf("SNKRDUNK", "スニダン"),
        "dunk" to listOf("SNKRDUNK", "スニダン"),
        "sunidan" to listOf("SNKRDUNK", "スニダン"),
        "すにだん" to listOf("SNKRDUNK", "スニダン"),

        // magi (Trading Cards)
        "magi" to listOf("magi", "マギ", "トレカ"),
        "toreka" to listOf("magi", "clove", "トレカ"),
        "まぎ" to listOf("magi", "マギ"),

        // Jimoty (Local)
        "jimo" to listOf("Jimoty", "ジモティー"),
        "jimoti" to listOf("Jimoty", "ジモティー"),
        "jimotei" to listOf("Jimoty", "ジモティー"),
        "じも" to listOf("Jimoty", "ジモティー"),
        "じもてぃー" to listOf("Jimoty", "ジモティー"),

        // ==========================================
        // 41. Shopping: Fashion & Apparel (ファッション)
        // ==========================================
        // ZOZOTOWN
        "zozo" to listOf("ZOZOTOWN", "ゾゾタウン", "ゾゾ"),
        "zo" to listOf("ZOZOTOWN", "ゾゾ"),
        "zozotown" to listOf("ZOZOTOWN", "ゾゾタウン"),
        "ぞぞ" to listOf("ZOZOTOWN", "ゾゾ"),
        "ぞぞたうん" to listOf("ZOZOTOWN", "ゾゾタウン"),

        // UNIQLO
        "uni" to listOf("UNIQLO", "ユニクロ"),
        "uniqlo" to listOf("UNIQLO", "ユニクロ"),
        "yunikuro" to listOf("UNIQLO", "ユニクロ"),
        "ゆに" to listOf("UNIQLO", "ユニクロ"),
        "ゆにくろ" to listOf("UNIQLO", "ユニクロ"),

        // GU
        "gu" to listOf("GU", "ジーユー"),
        "zi-yu-" to listOf("GU", "ジーユー"),
        "じーゆー" to listOf("GU", "ジーユー"),
        "じゆう" to listOf("GU", "ジーユー"), // 誤変換

        // SHEIN
        "shein" to listOf("SHEIN", "シーイン"),
        "shiin" to listOf("SHEIN", "シーイン"),
        "she" to listOf("SHEIN", "シーイン"),
        "しーいん" to listOf("SHEIN", "シーイン"),

        // GRL
        "grl" to listOf("GRL", "グレイル"),
        "gureiru" to listOf("GRL", "グレイル"),
        "ぐれいる" to listOf("GRL", "グレイル"),

        // SHOPLIST
        "shoplist" to listOf("SHOPLIST", "ショップリスト"),
        "shoppu" to listOf("SHOPLIST", "ショップリスト"),
        "しょっぷりすと" to listOf("SHOPLIST", "ショップリスト"),

        // ZARA / H&M
        "zara" to listOf("ZARA", "ザラ"),
        "hm" to listOf("H&M", "エイチアンドエム"),
        "handm" to listOf("H&M", "エイチアンドエム"),

        // Sports Brands
        "nike" to listOf("Nike", "SNKRS", "ナイキ"),
        "snkrs" to listOf("SNKRS", "Nike", "スニーカーズ"),
        "naiki" to listOf("Nike", "ナイキ"),
        "adi" to listOf("Adidas", "Confirmed", "アディダス"),
        "adidas" to listOf("Adidas", "アディダス"),
        "adidasu" to listOf("Adidas", "アディダス"),

        // Workman
        "work" to listOf("Workman", "Workman Plus", "ワークマン"),
        "wa-kuman" to listOf("Workman", "ワークマン"),
        "わーくまん" to listOf("Workman", "ワークマン"),

        // ==========================================
        // 42. Shopping: Electronics & PC (家電 / パソコン)
        // ==========================================
        // Yodobashi Camera
        "yodo" to listOf("Yodobashi", "ヨドバシ", "ヨドバシカメラ"),
        "yodobashi" to listOf("Yodobashi", "ヨドバシ"),
        "gold" to listOf("Yodobashi", "ゴールドポイント"),
        "よど" to listOf("Yodobashi", "ヨドバシ"),
        "よどばし" to listOf("Yodobashi", "ヨドバシ"),

        // Bic Camera
        "bic" to listOf("Bic Camera", "ビックカメラ"),
        "bikku" to listOf("Bic Camera", "ビックカメラ"),
        "big" to listOf("Bic Camera", "ビックカメラ"), // 誤記対応
        "びっく" to listOf("Bic Camera", "ビックカメラ"),

        // Yamada Denki
        "yamada" to listOf("Yamada Denki", "ヤマダ電機", "ヤマダ"),
        "yama" to listOf("Yamada Denki", "ヤマダ"),
        "やまだ" to listOf("Yamada Denki", "ヤマダ"),

        // Apple Store
        "apple" to listOf("Apple Store", "Apple", "アップル"),
        "store" to listOf("Apple Store", "Google Play Store", "ストア"),
        "appu" to listOf("Apple Store", "アップル"),
        "あっぷる" to listOf("Apple Store", "アップル"),

        // PC Parts / BTO
        "dos" to listOf("Dospara", "ドスパラ"),
        "dosupara" to listOf("Dospara", "ドスパラ"),
        "どすぱら" to listOf("Dospara", "ドスパラ"),
        "tsukumo" to listOf("Tsukumo", "ツクモ"),
        "つくも" to listOf("Tsukumo", "ツクモ"),
        "sof" to listOf("Sofmap", "ソフマップ"),
        "sofumappu" to listOf("Sofmap", "ソフマップ"),
        "そふまっぷ" to listOf("Sofmap", "ソフマップ"),
        "mouse" to listOf("Mouse Computer", "マウスコンピューター"),
        "dell" to listOf("Dell", "デル"),

        // ==========================================
        // 43. Shopping: Overseas / Cross-border (海外通販)
        // ==========================================
        // AliExpress
        "ali" to listOf("AliExpress", "アリエクスプレス", "アリエク"),
        "aliex" to listOf("AliExpress", "アリエク"),
        "ari" to listOf("AliExpress", "アリエク"),
        "ariek" to listOf("AliExpress", "アリエク"),
        "arieku" to listOf("AliExpress", "アリエク"),
        "あり" to listOf("AliExpress", "アリエク"),
        "ありえく" to listOf("AliExpress", "アリエク"),

        // Temu
        "temu" to listOf("Temu", "テム", "ティームー"),
        "tem" to listOf("Temu", "テム"),
        "temu-" to listOf("Temu", "ティームー"),
        "てむ" to listOf("Temu", "テム"),

        // iHerb
        "iherb" to listOf("iHerb", "アイハーブ"),
        "herb" to listOf("iHerb", "ハーブ"),
        "aiha" to listOf("iHerb", "アイハーブ"),
        "あいはーぶ" to listOf("iHerb", "アイハーブ"),

        // Buyee / Proxy
        "buyee" to listOf("Buyee", "Mercari", "バイイー"),
        "bai" to listOf("Buyee", "バイイー"),
        "proxy" to listOf("Buyee", "From Japan", "代行"),

        // ==========================================
        // 44. Shopping: Home & Daily Life (生活 / 日用品)
        // ==========================================
        // Nitori
        "nitori" to listOf("Nitori", "ニトリ"),
        "nito" to listOf("Nitori", "ニトリ"),
        "にとり" to listOf("Nitori", "ニトリ"),

        // IKEA
        "ikea" to listOf("IKEA", "イケア"),
        "ike" to listOf("IKEA", "イケア"),
        "ikeya" to listOf("IKEA", "イケア"), // 読み間違い対応
        "いけあ" to listOf("IKEA", "イケア"),

        // MUJI
        "muji" to listOf("MUJI", "無印良品", "無印"),
        "mujirushi" to listOf("MUJI", "無印良品"),
        "むじ" to listOf("MUJI", "無印"),
        "むじるし" to listOf("MUJI", "無印良品"),

        // 100 Yen Shops
        "daiso" to listOf("Daiso", "ダイソー"),
        "daiso-" to listOf("Daiso", "ダイソー"),
        "だいそー" to listOf("Daiso", "ダイソー"),
        "seria" to listOf("Seria", "セリア"),
        "cando" to listOf("Can Do", "キャンドゥ"),
        "hyakkin" to listOf("Daiso", "Seria", "100均"),

        // Drug Stores
        "matsu" to listOf("Matsukiyo", "Matsumoto Kiyoshi", "マツキヨ"),
        "kiyo" to listOf("Matsukiyo", "マツキヨ"),
        "ma" to listOf("Matsukiyo", "マツキヨ"),
        "welcia" to listOf("Welcia", "ウエルシア"),
        "ueru" to listOf("Welcia", "ウエルシア"),
        "sugi" to listOf("Sugi Pharmacy", "スギ薬局"),
        "cocokara" to listOf("Cocokara Fine", "ココカラファイン"),
        "kusuri" to listOf("Matsukiyo", "Welcia", "薬局", "ドラッグストア"),

        // Supermarkets / Conbini
        "aeon" to listOf("iAEON", "AEON", "イオン"),
        "ion" to listOf("iAEON", "AEON", "イオン"),
        "いおん" to listOf("iAEON", "イオン"),
        "ito" to listOf("Ito-Yokado", "イトーヨーカドー"),
        "yokado" to listOf("Ito-Yokado", "ヨーカドー"),
        "seiyu" to listOf("Seiyu", "Rakuten Seiyu", "西友"),
        "donki" to listOf("Don Quijote", "majica", "ドンキ"),
        "majica" to listOf("majica", "Don Quijote", "マジカ"),
        "seven" to listOf("7-Eleven", "セブンイレブン", "セブン"),
        "nanaco" to listOf("nanaco", "ナナコ"),
        "lawson" to listOf("Lawson", "Ponta", "ローソン"),
        "fami" to listOf("FamilyMart", "FamiPay", "ファミマ"),

        // ==========================================
        // 45. Shopping: Otaku & Hobby (オタク / グッズ)
        // ==========================================
        // Anime Goods
        "animate" to listOf("Animate", "Club Animate", "アニメイト"),
        "ani" to listOf("Animate", "アニメイト"),
        "anime" to listOf("Animate", "アニメイト"),
        "あにめいと" to listOf("Animate", "アニメイト"),

        // Doujin / Books
        "melon" to listOf("Melonbooks", "メロンブックス"),
        "meron" to listOf("Melonbooks", "メロン"),
        "めろん" to listOf("Melonbooks", "メロンブックス"),
        "tora" to listOf("Toranoana", "とらのあな"),
        "toranoana" to listOf("Toranoana", "とらのあな"),
        "tora" to listOf("Toranoana", "とら"),
        "とら" to listOf("Toranoana", "とらのあな"),
        "lashin" to listOf("Lashinbang", "らしんばん"),
        "rashin" to listOf("Lashinbang", "らしんばん"),

        // Used Goods / Hobby
        "suru" to listOf("Surugaya", "駿河屋"),
        "suruga" to listOf("Surugaya", "駿河屋"),
        "surugaya" to listOf("Surugaya", "駿河屋"),
        "するがや" to listOf("Surugaya", "駿河屋"),
        "manda" to listOf("Mandarake", "まんだらけ"),
        "mandarake" to listOf("Mandarake", "まんだらけ"),
        "ami" to listOf("AmiAmi", "あみあみ"),
        "amiami" to listOf("AmiAmi", "あみあみ"),
        "preban" to listOf("Premium Bandai", "プレバン"),
        "bandai" to listOf("Premium Bandai", "バンダイ"),
        "p-bandai" to listOf("Premium Bandai", "プレバン"),

        // ==========================================
        // 46. Shopping: Gift & Tickets (ギフト / チケット)
        // ==========================================
        "gift" to listOf("LINE Gift", "Amazon", "ギフト"),
        "linegift" to listOf("LINE Gift", "LINEギフト"),
        "gifuto" to listOf("LINE Gift", "ギフト"),
        "tikejam" to listOf("Ticket Jam", "チケジャム"),
        "jam" to listOf("Ticket Jam", "チケジャム"),
        "ticket" to listOf("Ticket Pia", "e+", "チケット"),
        "chiketto" to listOf("Ticket Pia", "チケット"),

        // ==========================================
        // 47. SNS: The Big Four (主要4大SNS / 入力揺らぎ完全対応)
        // ==========================================
        // Twitter / X (イーロンマスク変更対応)
        "x" to listOf("X", "Twitter", "エックス", "ツイッター"),
        "twit" to listOf("Twitter", "X", "ツイッター"),
        "twi" to listOf("Twitter", "X", "ツイート"),
        "tweet" to listOf("Twitter", "X", "ツイート"),
        "post" to listOf("X", "Twitter", "ポスト"),
        "blue" to listOf("Twitter", "Bluesky", "青い鳥"), // 旧アイコン意識
        "aka" to listOf("Twitter", "Instagram", "アカウント"), // 垢（あか）
        "ura" to listOf("Twitter", "X", "裏垢"), // 裏垢
        "main" to listOf("Twitter", "Instagram", "本垢"), // 本垢
        "sub" to listOf("Twitter", "Instagram", "サブ垢"), // サブ垢
        "tsuitta" to listOf("Twitter", "ツイッター"),
        "tuitta" to listOf("Twitter", "ツイッター"), // タイポ
        "tuitter" to listOf("Twitter", "ツイッター"), // タイポ

        // Instagram
        "insta" to listOf("Instagram", "インスタグラム", "インスタ"),
        "ig" to listOf("Instagram", "インスタ"),
        "gram" to listOf("Instagram", "インスタ"),
        "story" to listOf("Instagram", "Stories", "ストーリー"),
        "reel" to listOf("Instagram", "Reels", "リール"),
        "insuta" to listOf("Instagram", "インスタ"),
        "intsa" to listOf("Instagram", "インスタ"), // よくあるタイポ
        "baye" to listOf("Instagram", "映え"), // インスタ映え

        // Facebook
        "fb" to listOf("Facebook", "フェイスブック"),
        "face" to listOf("Facebook", "フェイスブック"),
        "fbook" to listOf("Facebook", "フェイスブック"),
        "meta" to listOf("Facebook", "Meta", "メタ"),

        // TikTok
        "tik" to listOf("TikTok", "ティックトック"),
        "tok" to listOf("TikTok", "ティックトック"),
        "tt" to listOf("TikTok", "ティックトック"),
        "douyin" to listOf("TikTok", "抖音"), // 中国版名称
        "teikku" to listOf("TikTok", "ティックトック"),
        "tikutokku" to listOf("TikTok", "ティックトック"),

        // ==========================================
        // 48. SNS: Decentralized & Next Gen (分散型 / 新興)
        // ==========================================
        // Threads
        "th" to listOf("Threads", "Instagram", "スレッズ"),
        "thread" to listOf("Threads", "スレッズ"),
        "sure" to listOf("Threads", "スレッズ"), // スレ
        "surezzu" to listOf("Threads", "スレッズ"),

        // Bluesky
        "bsky" to listOf("Bluesky", "ブルースカイ"),
        "sky" to listOf("Bluesky", "Sky", "空"),
        "ao" to listOf("Bluesky", "青空"),
        "buru" to listOf("Bluesky", "ブルースカイ"),

        // Misskey / Mastodon (Fediverse)
        "miss" to listOf("Misskey", "Misskey.io", "ミスキー"),
        "mk" to listOf("Misskey", "ミスキー"),
        "io" to listOf("Misskey.io", "io", "イオ"),
        "misuki" to listOf("Misskey", "ミスキー"),
        "mstdn" to listOf("Mastodon", "マストドン"),
        "don" to listOf("Mastodon", "丼", "ドン"),
        "fedibird" to listOf("Fedibird", "フェディバード"),

        // ==========================================
        // 49. SNS: Real & Casual (リアル / 日常共有)
        // ==========================================
        // BeReal
        "be" to listOf("BeReal", "ビーリアル"),
        "real" to listOf("BeReal", "リアル"),
        "br" to listOf("BeReal", "ビーリアル"),
        "biri" to listOf("BeReal", "ビリ"), // 略称
        "bi-ria" to listOf("BeReal", "ビーリアル"),
        "biria" to listOf("BeReal", "ビーリアル"),

        // Location Sharing (Zenly alternatives)
        "nau" to listOf("whoo", "NauNau", "Snapchat", "位置情報"), // ナウ
        "whoo" to listOf("whoo", "フー"),
        "ichi" to listOf("Google Maps", "Snapchat", "位置情報"),

        // SNOW / Camera Apps
        "snow" to listOf("SNOW", "スノー"),
        "soda" to listOf("SODA", "ソーダ"),
        "ulike" to listOf("Ulike", "ユーライク"),
        "mori" to listOf("SNOW", "Ulike", "盛れる"),

        // ==========================================
        // 50. SNS: Live Streaming (ライブ配信 / Vライバー)
        // ==========================================
        "twitch" to listOf("Twitch", "ツイッチ"),
        "twi" to listOf("Twitch", "ツイッチ"), // Twitterと被るが候補には出す
        "cas" to listOf("TwitCasting", "ツイキャス"),
        "moi" to listOf("TwitCasting", "モイ"), // ツイキャスの挨拶
        "twitcas" to listOf("TwitCasting", "ツイキャス"),
        "17" to listOf("17LIVE", "イチナナ"),
        "ichi" to listOf("17LIVE", "イチナナ"),
        "poco" to listOf("Pococha", "ポコチャ"),
        "showroom" to listOf("SHOWROOM", "ショールーム"),
        "sr" to listOf("SHOWROOM", "ショールーム"),
        "spoon" to listOf("Spoon", "スプーン"),
        "reality" to listOf("REALITY", "リアリティ"),
        "iriam" to listOf("IRIAM", "イリアム"),
        "whowatch" to listOf("Whowatch", "ふわっち"),
        "fuwa" to listOf("Whowatch", "ふわっち"),
        "mirrativ" to listOf("Mirrativ", "ミラティブ"),
        "mira" to listOf("Mirrativ", "ミラティブ"),

        // ==========================================
        // 51. SNS: Community & Text (コミュニティ / 掲示板)
        // ==========================================
        // Discord
        "dc" to listOf("Discord", "ディスコード"),
        "disco" to listOf("Discord", "ディスコード"),
        "mushi" to listOf("Discord", "LINE", "通話"), // 通話の隠語（無視→虫→通話虫）
        "tsuwa" to listOf("Discord", "LINE", "通話"),

        // Reddit
        "red" to listOf("Reddit", "レディット"),
        "reddit" to listOf("Reddit", "レディット"),
        "kaigai" to listOf("Reddit", "海外の反応"),

        // 5ch / Matome
        "5ch" to listOf("ChMate", "5chan", "5ちゃんねる"),
        "2ch" to listOf("ChMate", "2chan", "2ちゃんねる"),
        "mate" to listOf("ChMate", "JaneStyle", "メイト"),
        "chmate" to listOf("ChMate", "5ちゃんねる"),
        "keijiban" to listOf("ChMate", "掲示板"),

        // Note / Blog
        "note" to listOf("note", "Keep Notes", "ノート"), // Google Keepと競合するがnoteも重要
        "noto" to listOf("note", "ノート"),
        "ameblo" to listOf("Ameba Blog", "アメブロ"),
        "hatena" to listOf("Hatena Bookmark", "はてなブックマーク"),
        "hatebu" to listOf("Hatena Bookmark", "はてブ"),
        "qiita" to listOf("Qiita", "キータ"), // 技術系SNS
        "zenn" to listOf("Zenn", "ゼン"),

        // ==========================================
        // 52. SNS: Question & Anonymous (質問箱 / 匿名)
        // ==========================================
        "peing" to listOf("Peing", "Question Box", "質問箱"),
        "shitsumon" to listOf("Peing", "Marshmallow", "質問"),
        "mash" to listOf("Marshmallow", "マシュマロ"),
        "maro" to listOf("Marshmallow", "マロ"),
        "yay" to listOf("Yay!", "イェイ", "ひま部"),
        "hima" to listOf("Yay!", "Gravity", "暇つぶし"),
        "gravity" to listOf("Gravity", "グラビティ"),
        "gura" to listOf("Gravity", "グラ"),

        // ==========================================
        // 53. SNS: Business & Professional (ビジネス)
        // ==========================================
        "linked" to listOf("LinkedIn", "リンクトイン"),
        "in" to listOf("LinkedIn", "イン"),
        "wanted" to listOf("Wantedly", "ウォンテッドリー"),
        "visit" to listOf("Wantedly Visit", "ウォンテッドリー"),
        "eight" to listOf("Eight", "名刺管理"),
        "youten" to listOf("YOUTRUST", "ユートラスト"),
        "trust" to listOf("YOUTRUST", "トラスト"),

        // ==========================================
        // 54. SNS: Matching & Dating (マッチング / 出会い)
        // ==========================================
        "tinder" to listOf("Tinder", "ティンダー"),
        "tin" to listOf("Tinder", "ティンダー"),
        "pairs" to listOf("Pairs", "ペアーズ"),
        "pea" to listOf("Pairs", "ペアーズ"),
        "taple" to listOf("Tapple", "タップル"),
        "tap" to listOf("Tapple", "タップル"),
        "with" to listOf("with", "ウィズ"),
        "omiai" to listOf("Omiai", "オミアイ"),
        "bumble" to listOf("Bumble", "バンブル"),
        "grindr" to listOf("Grindr", "グラインダー"), // LGBTQ+
        "nai" to listOf("Tinder", "Pairs", "ナイモノ"), // 9monstersなど
        "koikatsu" to listOf("Pairs", "Tinder", "恋活"),
        "deai" to listOf("Tinder", "Pairs", "出会い"),

        // ==========================================
        // 55. SNS: Hobby & Audio (趣味 / 音声)
        // ==========================================
        "club" to listOf("Clubhouse", "クラブハウス"), // まだ検索する人はいる
        "stand" to listOf("stand.fm", "スタエフ"),
        "fm" to listOf("stand.fm", "ラジオ"),
        "voicy" to listOf("Voicy", "ボイシー"),
        "pixiv" to listOf("pixiv", "ピクシブ"),
        "sike" to listOf("pixiv", "スケベ"), // 隠語対応
        "pict" to listOf("PictSQUARE", "ピクスク"),
        "book" to listOf("Bookmeter", "読書メーター"),
        "film" to listOf("Filmarks", "フィルマークス"),
        "filma" to listOf("Filmarks", "映画")
    )
        "filma" to listOf("Filmarks", "映画"),

        // ==========================================
        // 56. Finance: Banks (主要銀行 / ネット銀行)
        // ==========================================
        // Mega Banks
        "ufj" to listOf("Mitsubishi UFJ", "三菱UFJ銀行", "三菱"),
        "mufg" to listOf("Mitsubishi UFJ", "三菱UFJ銀行"),
        "mitsubishi" to listOf("Mitsubishi UFJ", "三菱UFJ銀行"),
        "smbc" to listOf("SMBC", "Sumitomo Mitsui", "三井住友銀行", "三井"),
        "mitsui" to listOf("SMBC", "三井住友銀行"),
        "mizuho" to listOf("Mizuho", "Mizuho Direct", "みずほ銀行", "みずほ"),
        "mizu" to listOf("Mizuho", "みずほ銀行"),
        "risona" to listOf("Resona", "りそな銀行", "りそな"),
        "reso" to listOf("Resona", "りそな"),
        "yu" to listOf("Japan Post Bank", "Yucho", "ゆうちょ銀行", "ゆうちょ"),
        "yucho" to listOf("Japan Post Bank", "ゆうちょ銀行"),
        "yuutyo" to listOf("Japan Post Bank", "ゆうちょ銀行"),

        // Net Banks
        "sbi" to listOf("SBI Sumishin Net Bank", "SBI", "住信SBIネット銀行"),
        "sumishin" to listOf("SBI Sumishin Net Bank", "住信SBI"),
        "rakutenbank" to listOf("Rakuten Bank", "楽天銀行"),
        "rbank" to listOf("Rakuten Bank", "楽天銀行"),
        "paypaybank" to listOf("PayPay Bank", "PayPay銀行"),
        "japannet" to listOf("PayPay Bank", "ジャパンネット銀行"), // 旧名
        "sony" to listOf("Sony Bank", "ソニー銀行"),
        "sevenbank" to listOf("Seven Bank", "セブン銀行"),
        "aeonbank" to listOf("AEON Bank", "イオン銀行"),
        "jibun" to listOf("au Jibun Bank", "auじぶん銀行"),

        // ==========================================
        // 57. Finance: Securities & Tax (証券 / 税金)
        // ==========================================
        "sbisec" to listOf("SBI Securities", "SBI証券"),
        "kabu" to listOf("SBI Securities", "Rakuten Securities", "株", "株価"),
        "stock" to listOf("Stock", "株"),
        "rakutensec" to listOf("Rakuten Securities", "楽天証券", "iSPEED"),
        "ispeed" to listOf("Rakuten Securities", "iSPEED"),
        "monex" to listOf("Monex", "マネックス証券"),
        "nomura" to listOf("Nomura", "野村證券"),
        "daiwa" to listOf("Daiwa", "大和証券"),
        "etax" to listOf("e-Tax", "確定申告", "国税庁"),
        "tax" to listOf("e-Tax", "Myna Portal", "税金"),
        "myna" to listOf("Myna Portal", "マイナポータル", "マイナンバー"),
        "koukin" to listOf("PayPay", "公金受取"),

        // ==========================================
        // 58. Manga & E-Books: Detailed (マンガアプリ特化)
        // ==========================================
        // Shueisha
        "jump" to listOf("Shonen Jump+", "Jump+", "ジャンプ+", "ジャンプ"),
        "janpu" to listOf("Shonen Jump+", "ジャンプ"),
        "plus" to listOf("Shonen Jump+", "NHK Plus", "プラス"), // 文脈依存
        "zebrack" to listOf("Zebrack", "ゼブラック"),
        "zebra" to listOf("Zebrack", "ゼブラック"),
        "yanjan" to listOf("Young Jump", "ヤンジャン"),

        // Kodansha
        "maga" to listOf("Magazine Pocket", "マガポケ", "マガジン"),
        "magapoke" to listOf("Magazine Pocket", "マガポケ"),
        "poke" to listOf("Magazine Pocket", "Pokemon", "ポケ"), // ポケモンと被るが候補へ
        "days" to listOf("Comic DAYS", "コミックDAYS"),

        // Shogakukan
        "sunday" to listOf("Sunday Webry", "サンデーうぇぶり", "サンデー"),
        "webry" to listOf("Sunday Webry", "うぇぶり"),
        "mangaone" to listOf("MangaONE", "マンガワン"),
        "one" to listOf("MangaONE", "Google One", "ワン"),

        // Others
        "piccoma" to listOf("Piccoma", "ピッコマ"),
        "pikko" to listOf("Piccoma", "ピッコマ"),
        "line" to listOf("LINE Manga", "LINEマンガ"),
        "mecha" to listOf("Mecha Comic", "めちゃコミック"),
        "cmoa" to listOf("Comic Cmoa", "コミックシーモア"),
        "ebook" to listOf("eBookJapan", "イーブック"),
        "renta" to listOf("Renta!", "レンタ"),
        "sukima" to listOf("Sukima", "スキマ"),
        "pixivcomic" to listOf("pixiv Comic", "pixivコミック"),
        "picomi" to listOf("pixiv Comic", "ピクコミ"),
        "ganma" to listOf("GANMA!", "ガンマ"),
        "comico" to listOf("comico", "コミコ"),

        // ==========================================
        // 59. Food Chains: Gourmet (飲食チェーン / カフェ)
        // ==========================================
        // Gyudon (Beef Bowl)
        "suki" to listOf("Sukiya", "すき家"),
        "sukiya" to listOf("Sukiya", "すき家"),
        "yoshi" to listOf("Yoshinoya", "吉野家"),
        "yoshinoya" to listOf("Yoshinoya", "吉野家"),
        "gyu" to listOf("Yoshinoya", "Matsuya", "Sukiya", "牛丼"),
        "matsu" to listOf("Matsuya", "Matsukiyo", "松屋"), // マツキヨと被る
        "matsuya" to listOf("Matsuya", "松屋"),
        "nakau" to listOf("Nakau", "なか卯"),

        // Sushi
        "sushiro" to listOf("Sushiro", "スシロー"),
        "kura" to listOf("Kura Sushi", "くら寿司"),
        "hama" to listOf("Hama Sushi", "はま寿司"),
        "kappa" to listOf("Kappa Sushi", "かっぱ寿司"),
        "sushi" to listOf("Sushiro", "Kura Sushi", "Hama Sushi", "寿司"),

        // Cafe
        "star" to listOf("Starbucks", "スタバ", "スターバックス"),
        "stb" to listOf("Starbucks", "スタバ"),
        "doutor" to listOf("Doutor", "ドトール"),
        "komeda" to listOf("Komeda", "コメダ珈琲店", "コメダ"),
        "tully" to listOf("Tully's", "タリーズ"),
        "cafe" to listOf("Starbucks", "Maps", "カフェ", "喫茶店"),

        // Family Restaurant / Fast Food
        "saize" to listOf("Saizeriya", "サイゼリヤ", "サイゼ"),
        "gusto" to listOf("Gusto", "ガスト"),
        "bamiyan" to listOf("Bamiyan", "バーミヤン"),
        "coco" to listOf("CoCo Ichibanya", "CoCo壱番屋", "ココイチ"),
        "curry" to listOf("CoCo Ichibanya", "カレー"),
        "mos" to listOf("Mos Burger", "モスバーガー", "モス"),
        "kfc" to listOf("KFC", "Kentucky", "ケンタッキー"),
        "kenta" to listOf("KFC", "ケンタ"),
        "chicken" to listOf("KFC", "チキン"),

        // Pizza
        "domino" to listOf("Domino's Pizza", "ドミノピザ"),
        "pizza" to listOf("Domino's Pizza", "Pizza Hut", "ピザ"),
        "hut" to listOf("Pizza Hut", "ピザハット"),

        // ==========================================
        // 60. Travel: Airlines & Booking (旅行 / 航空券)
        // ==========================================
        // Airlines (Domestic)
        "ana" to listOf("ANA", "All Nippon Airways", "全日空", "エーエヌエー"),
        "jal" to listOf("JAL", "Japan Airlines", "日本航空", "ジャル"),
        "peach" to listOf("Peach", "ピーチ", "桃"),
        "jet" to listOf("Jetstar", "ジェットスター"),
        "star" to listOf("Jetstar", "Starbucks", "スター"),
        "sky" to listOf("Skymark", "スカイマーク"),
        "lcc" to listOf("Peach", "Jetstar", "LCC", "格安航空"),

        // Booking Sites (OTA)
        "jalan" to listOf("Jalan", "じゃらん"),
        "jaran" to listOf("Jalan", "じゃらん"),
        "rakutentravel" to listOf("Rakuten Travel", "楽天トラベル"),
        "travel" to listOf("Rakuten Travel", "Jalan", "Tripadvisor", "旅行"),
        "trip" to listOf("Trip.com", "Tripadvisor", "トリップドットコム"),
        "agoda" to listOf("Agoda", "アゴダ"),
        "booking" to listOf("Booking.com", "ブッキングドットコム"),
        "expedia" to listOf("Expedia", "エクスペディア"),
        "airbnb" to listOf("Airbnb", "エアビー"),
        "minpaku" to listOf("Airbnb", "民泊"),

        // ==========================================
        // 61. Education & Dictionaries (学習 / 辞書)
        // ==========================================
        // Translation
        "deep" to listOf("DeepL", "ディープエル"),
        "deepl" to listOf("DeepL", "ディープエル"),
        "googletrans" to listOf("Google Translate", "翻訳"),
        "honyaku" to listOf("Google Translate", "DeepL", "翻訳"),
        
        // Dictionaries
        "weblio" to listOf("Weblio", "辞書"),
        "koto" to listOf("Kotobank", "コトバンク"),
        "jisho" to listOf("Weblio", "Kotobank", "辞書"),
        "eiwa" to listOf("Dictionary", "英和"),
        "waei" to listOf("Dictionary", "和英"),

        // Learning
        "duo" to listOf("Duolingo", "デュオリンゴ"),
        "study" to listOf("Studyplus", "スタディプラス", "勉強"),
        "mikan" to listOf("Mikan", "英単語"),
        "abceed" to listOf("abceed", "エービーシード", "TOEIC"),
        "toeic" to listOf("abceed", "Santa", "TOEIC"),
        "els" to listOf("Elsa Speak", "エルサ"),

        // ==========================================
        // 62. Health & Beauty (健康 / 美容)
        // ==========================================
        // Reservation
        "hot" to listOf("Hot Pepper Beauty", "Hot Pepper", "ホットペッパー"),
        "beauty" to listOf("Hot Pepper Beauty", "ビューティー"),
        "salon" to listOf("Hot Pepper Beauty", "サロン", "美容院"),
        "biyou" to listOf("Hot Pepper Beauty", "美容院"),

        // Diet / Fitness
        "asken" to listOf("Asken", "あすけん"),
        "asu" to listOf("Asken", "あすけん"),
        "diet" to listOf("Asken", "MyFitnessPal", "ダイエット"),
        "fit" to listOf("Google Fit", "Fitbit", "フィットネス"),
        "coke" to listOf("Coke ON", "コークオン"),
        "walk" to listOf("Coke ON", "Dragon Quest Walk", "歩数計"),
        "sanpo" to listOf("Pikmin Bloom", "Dragon Quest Walk", "散歩"),
        "pikmin" to listOf("Pikmin Bloom", "ピクミン"),

        // ==========================================
        // 63. System: Advanced Tools (上級者向け)
        // ==========================================
        // File Managers
        "file" to listOf("Files by Google", "Solid Explorer", "ファイル"),
        "solid" to listOf("Solid Explorer", "ソリッド"),
        "cx" to listOf("Cx File Explorer", "Cx"),
        "xplore" to listOf("X-plore", "エクスプローラー"),
        "manager" to listOf("Files", "マネージャー"),

        // Launchers
        "nova" to listOf("Nova Launcher", "ノヴァ"),
        "launcher" to listOf("Nova Launcher", "Microsoft Launcher", "ランチャー"),
        "niagara" to listOf("Niagara Launcher", "ナイアガラ"),

        // Automation
        "tasker" to listOf("Tasker", "タスカー"),
        "macro" to listOf("MacroDroid", "マクロドロイド"),
        "auto" to listOf("Tasker", "MacroDroid", "自動化"),
        "ifttt" to listOf("IFTTT", "イフト"),

        // Network / Utils
        "speed" to listOf("Speedtest", "スピードテスト"),
        "fing" to listOf("Fing", "ネットワーク"),
        "wifi" to listOf("Wi-Fi", "Settings", "ワイファイ"),
        "adb" to listOf("Bugjaeger", "ADB", "デバッグ"),
        "dev" to listOf("Developer Options", "開発者向けオプション"),

        // ==========================================
        // 64. Entertainment: Streaming (VOD / 動画)
        // ==========================================
        // Major VOD
        "net" to listOf("Netflix", "Disney+", "ネットフリックス"),
        "netflix" to listOf("Netflix", "ネットフリックス"),
        "netofuri" to listOf("Netflix", "ネトフリ"),
        "ne" to listOf("Netflix", "ネトフリ"),
        "prime" to listOf("Prime Video", "Amazon", "プライムビデオ"),
        "video" to listOf("Prime Video", "YouTube", "ビデオ"),
        "amapura" to listOf("Prime Video", "アマプラ"),
        "hulu" to listOf("Hulu", "Disney+", "フールー"),
        "disney" to listOf("Disney+", "ディズニー"),
        "dizuni" to listOf("Disney+", "ディズニー"),
        "unext" to listOf("U-NEXT", "ユーネクスト"),
        "yu-ne" to listOf("U-NEXT", "ユーネクスト"),
        "dazn" to listOf("DAZN", "ダゾーン"),
        "dazon" to listOf("DAZN", "ダゾーン"),

        // TV / Anime / Niconico
        "tver" to listOf("TVer", "ティーバー"),
        "abema" to listOf("Abema", "アベマ"),
        "nico" to listOf("niconico", "ニコニコ"),
        "niko" to listOf("niconico", "ニコニコ"),
        "douga" to listOf("niconico", "YouTube", "動画"),
        "nhk" to listOf("NHK Plus", "NHK News", "NHK"),

        // ==========================================
        // 65. Entertainment: Music & Radio (音楽 / ラジオ)
        // ==========================================
        "spoti" to listOf("Spotify", "スポティファイ"),
        "supo" to listOf("Spotify", "スポティファイ"),
        "music" to listOf("Spotify", "Apple Music", "YouTube Music", "音楽"),
        "ongaku" to listOf("Spotify", "Music", "音楽"),
        "apple" to listOf("Apple Music", "Apple", "アップル"),
        "ringo" to listOf("Apple Music", "Apple", "林檎"), // 隠語
        "ytm" to listOf("YouTube Music", "ワイティーエム"),
        "awa" to listOf("AWA", "アワ"),
        "radiko" to listOf("radiko", "ラジコ"),
        "radio" to listOf("radiko", "ラジオ"),
        "shazam" to listOf("Shazam", "シャザム"),
        "uta" to listOf("Spotify", "Music", "歌"),

        // ==========================================
        // 66. Productivity: Office & Work (仕事 / 効率化)
        // ==========================================
        // Google Workspace
        "doc" to listOf("Google Docs", "Documents", "ドキュメント"),
        "sheet" to listOf("Google Sheets", "Excel", "スプレッドシート"),
        "slide" to listOf("Google Slides", "PowerPoint", "スライド"),
        "drive" to listOf("Google Drive", "ドライブ"),
        "keep" to listOf("Google Keep", "Keep Notes", "キープ"),
        "cal" to listOf("Google Calendar", "Calendar", "カレンダー"),
        "yotei" to listOf("Google Calendar", "予定"),

        // Microsoft Office
        "word" to listOf("Word", "Microsoft 365", "ワード"),
        "excel" to listOf("Excel", "Microsoft 365", "エクセル"),
        "power" to listOf("PowerPoint", "パワーポイント"),
        "ppt" to listOf("PowerPoint", "パワポ"),
        "office" to listOf("Microsoft 365", "Office", "オフィス"),

        // Tools
        "notion" to listOf("Notion", "ノーション"),
        "zoom" to listOf("Zoom", "ズーム"),
        "calc" to listOf("Calculator", "電卓", "計算"),
        "keisan" to listOf("Calculator", "計算"),
        "memo" to listOf("Keep Notes", "Notes", "メモ"),

        // ==========================================
        // 67. News, Weather & Disaster (ニュース / 天気 / 防災)
        // ==========================================
        // News
        "news" to listOf("Yahoo! News", "SmartNews", "Gunosy", "ニュース"),
        "nyu-su" to listOf("Yahoo! News", "SmartNews", "ニュース"),
        "smart" to listOf("SmartNews", "スマートニュース"),
        "suma" to listOf("SmartNews", "スマニュー"),
        "gunosy" to listOf("Gunosy", "グノシー"),

        // Weather
        "weather" to listOf("Weather", "Yahoo! Weather", "天気"),
        "tenki" to listOf("Yahoo! Weather", "Weathernews", "天気"),
        "ame" to listOf("Yahoo! Weather", "Amemiru", "雨雲"),
        "wn" to listOf("Weathernews", "ウェザーニュース"),
        "weza" to listOf("Weathernews", "ウェザー"),

        // Disaster Prevention
        "nerv" to listOf("NERV", "ネルフ", "防災"),
        "bousai" to listOf("Yahoo! Disaster Info", "NERV", "防災"),
        "jishin" to listOf("NERV", "Yahoo! Disaster Info", "地震"),

        // ==========================================
        // 68. Food & Delivery (飲食 / デリバリー)
        // ==========================================
        "uber" to listOf("Uber Eats", "Uber", "ウーバー"),
        "eats" to listOf("Uber Eats", "イーツ"),
        "demae" to listOf("Demae-can", "出前館"),
        "mac" to listOf("McDonald's", "マクドナルド"),
        "makku" to listOf("McDonald's", "マック"),
        "makudo" to listOf("McDonald's", "マクド"),
        "burger" to listOf("McDonald's", "Mos Burger", "バーガー"),

        // ==========================================
        // 69. Map & Navigation (地図 / 移動 / 交通)
        // ==========================================
        // Maps
        "map" to listOf("Google Maps", "Yahoo! Map", "地図", "マップ"),
        "chizu" to listOf("Google Maps", "地図"),
        "go" to listOf("Google Maps", "Pokemon GO", "ゴー"),

        // Navigation / Transit
        "navi" to listOf("Google Maps", "Navitime", "Yahoo! CarNavi", "ナビ"),
        "norikae" to listOf("Yahoo! Transit", "Jorudan", "乗換案内"),
        "transit" to listOf("Yahoo! Transit", "Google Maps", "乗換"),
        "eki" to listOf("Yahoo! Transit", "Suica", "駅"),

        // IC Cards / Taxi
        "suica" to listOf("Suica", "Mobile Suica", "スイカ"),
        "pasmo" to listOf("PASMO", "Mobile PASMO", "パスモ"),
        "ic" to listOf("Suica", "PASMO", "ICOCA"),
        "taxi" to listOf("GO", "Uber", "タクシー"),
        "go" to listOf("GO", "Pokemon GO", "ゴー"), // GOタクシーも対応
        
        // ==========================================
        // 70. Games: Major Titles (人気ゲーム)
        // ==========================================
        "poke" to listOf("Pokemon GO", "Pokemon Sleep", "ポケモン"),
        "mon" to listOf("Monster Hunter Now", "Pokemon GO", "モンハン"),
        "mh" to listOf("Monster Hunter Now", "モンハン"),
        "genshin" to listOf("Genshin Impact", "原神"),
        "hara" to listOf("Genshin Impact", "原神"),
        "uma" to listOf("Uma Musume", "ウマ娘"),
        "fate" to listOf("FGO", "Fate/Grand Order"),
        "fgo" to listOf("FGO", "Fate/Grand Order"),
        "pazudora" to listOf("Puzzle & Dragons", "パズドラ"),
        "puzz" to listOf("Puzzle & Dragons", "パズドラ"),
        "game" to listOf("Google Play Games", "Game Launcher", "ゲーム")
    )
}
