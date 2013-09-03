/**
 * 
 */
package tr.com.srdc.mdr.core.util;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @author anil
 * 
 *         Enumeration of ISO 639-2 codes. Populated from <a
 *         href="http://www.loc.gov/standards/iso639-2/php/code_list.php">the
 *         LOC (Library of Congress) page</a>.
 * 
 */
public enum Language {

	Afar("aar"), Abkhazian("abk"), Acehnese("ace"), Acoli("ach"), Adangme("ada"), Adyghe(
			"ady"), Afro_Asiatic_languages("afa"), Afrihili("afh"), Afrikaans(
			"afr"), Ainu("ain"), Akan("aka"), Akkadian("akk"), Albanian("alb"), Aleut(
			"ale"), Algonquian_languages("alg"), Southern_Altai("alt"), Amharic(
			"amh"), English_Old("ang"), Angika("anp"), Apache_languages("apa"), Arabic(
			"ara"), Official_Aramaic("arc"), Aragonese("arg"), Armenian("arm"), Mapudungun(
			"arn"), Arapaho("arp"), Artificial_languages("art"), Arawak("arw"), Assamese(
			"asm"), Asturian("ast"), Athabaskan_languages("ath"), Australian_languages(
			"aus"), Avaric("ava"), Avestan("ave"), Awadhi("awa"), Aymara("aym"), Azerbaijani(
			"aze"), Banda_languages("bad"), Bamileke_languages("bai"), Bashkir(
			"bak"), Baluchi("bal"), Bambara("bam"), Balinese("ban"), Basque(
			"baq"), Basa("bas"), Baltic_languages("bat"), Beja("bej"), Belarusian(
			"bel"), Bemba("bem"), Bengali("ben"), Berber_languages("ber"), Bhojpuri(
			"bho"), Bihari_languages("bih"), Bikol("bik"), Bini("bin"), Bislama(
			"bis"), Siksika("bla"), Bantu_languages("bnt"), Tibetan("bod"), Bosnian(
			"bos"), Braj("bra"), Breton("bre"), Batak_languages("btk"), Buriat(
			"bua"), Buginese("bug"), Bulgarian("bul"), Burmese("bur"), Blin(
			"byn"), Caddo("cad"), Central_American_Indian_languages("cai"), Galibi_Carib(
			"car"), Catalan("cat"), Caucasian_languages("cau"), Cebuano("ceb"), Celtic_languages(
			"cel"), Chamorro("cha"), Chibcha("chb"), Chechen("che"), Chagatai(
			"chg"), Chinese("chi"), Chuukese("chk"), Mari("chm"), Chinook_Jargon(
			"chn"), Choctaw("cho"), Chipewyan("chp"), Cherokee("chr"), Church_Slavonic(
			"chu"), Chuvash("chv"), Cheyenne("chy"), Chamic_languages("cmc"), Coptic(
			"cop"), Cornish("cor"), Corsican("cos"), Creoles_and_pidgins_English_based(
			"cpe"), Creoles_and_pidgins_French_based("cpe"), Creoles_and_pidgins_Portuguese_based(
			"cpp"), Cree("cre"), Crimean_Tatar("crh"), Creoles_and_pidgins(
			"crp"), Kashubian("csb"), Cushitic_languages("cus"), Czech("cze"), Dakota(
			"dak"), Danish("dan"), Dargwa("dar"), Land_Dayak_languages("day"), Delaware(
			"del"), Slave("den"), Dogrib("dgr"), Dinka("din"), Divehi("div"), Dogri(
			"doi"), Dravidian_languages("dra"), Lower_Sorbian("dsb"), Duala(
			"dua"), Dutch_Middle("dum"), Dutch_Flemish("dut"), Dyula("dyu"), Dzongkha(
			"dzo"), Efik("efi"), Egyptian("egy"), Ekajuk("eka"), Elamite("elx"), English(
			"eng"), Middle_English("enm"), Esperanto("epo"), Estonian("est"), Ewe(
			"ewe"), Ewondo("ewo"), Fang("fan"), Faroese("fao"), Fanti("fat"), Fijian(
			"fij"), Filipino("fil"), Finnish("fin"), Finno_Ugric_languages(
			"fiu"), Fon("fon"), French("fre"), French_Middle("frm"), French_Old(
			"fro"), Northern_Frisian("frr"), Eastern_Frisian("frs"), Western_Frisian(
			"fry"), Fulah("ful"), Friulian("fur"), Ga("gaa"), Gayo("gay"), Gbaya(
			"gba"), Germanic_languages("gem"), Georgian("geo"), German("ger"), Geez(
			"ges"), Gilbertes_Kiribati("gil"), Scottish_Gaelic("gla"), Irish(
			"gle"), Galician("glg"), Manx("glv"), German_Middle_High("gmh"), German_Old_High(
			"goh"), Gondi("gon"), Gorontalo("gor"), Gothic("got"), Grebo("grb"), Greek_Ancient(
			"grc"), Greek_Modern("gre"), Guarani("grn"), Swiss_German("gsw"), Gujarati(
			"guj"), Gwichin("gwi"), Haida("hai"), Haitian_Creole("hat"), Hausa(
			"hau"), Hawaiian("haw"), Hebrew("heb"), Herero("her"), Hiligaynon(
			"hil"), Himachali_languages("him"), Hindi("hin"), Hittite("hit"), Hmong(
			"hmn"), Hiri_Motu("hmo"), Croatian("hrv"), Upper_Sorbian("hsb"), Hungarian(
			"hun"), Hupa("hup"), Iban("iba"), Igbo("ibo"), Icelandic("ice"), Ido(
			"ido"), Sichuan_Yi("iii"), Ijo_languages("ijo"), Inuktitut("iku"), Interlingue(
			"ile"), Iloko("ilo"), Interlingua("ina"), Indic_languages("inc"), Indonesian(
			"ind"), Indo_European_languages("ine"), Ingush("inh"), Inupiaq(
			"ipk"), Iranian_languages("ira"), Iroquoian_languages("iro"), Italian(
			"ita"), Javanese("jav"), Lojban("jbo"), Japanese("jpn"), Judaeo_Persian(
			"jpr"), Judeo_Arabic("jrb"), Kara_Kalpak("kaa"), Kabyle("kab"), Kachin(
			"kac"), Greenlandic("kal"), Kamba("kam"), Kannada("kan"), Karen_languages(
			"kar"), Kashmiri("kas"), Kanuri("kau"), Kawi("kaw"), Kazakh("kaz"), Circassian(
			"kbd"), Khasi("kha"), Khoisan_languages("khi"), Central_Khmer("khm"), Khotanese(
			"kho"), Kikuyu("kik"), Kinyarwanda("kin"), Kirghiz("kir"), Kimbundu(
			"kmb"), Konkani("kok"), Komi("kom"), Kongo("kon"), Korean("kor"), Kosraean(
			"kos"), Kpelle("kpe"), Karachay_Balkar("krc"), Karelian("krl"), Kru_languages(
			"kro"), Kurukh("kur"), Kuanyama("kua"), Kumyk("kum"), Kurdish("kur"), Kutenai(
			"kut"), Ladino("lad"), Lahnda("lah"), Lamba("lam"), Lao("lao"), Latin(
			"lat"), Latvian("lat"), Lezghian("lez"), Limburgish("lim"), Lingala(
			"lin"), Lithuanian("lit"), Mongo("lol"), Lozi("loz"), Luxembourgish(
			"ltz"), Luba_Lulua("lua"), Luba_Katanga("lub"), Ganda("lug"), Luiseno(
			"lui"), Lunda("lun"), Luo("luo"), Lushai("lus"), Macedonian("mac"), Madurese(
			"mad"), Magahi("mag"), Marshallese("mah"), Maithili("mai"), Makasar(
			"mak"), Malayalam("mal"), Mandingo("man"), Maori("mao"), Austronesian_languages(
			"map"), Marathi("mar"), Maasai("mas"), Malay("may"), Moksha("mdf"), Mandar(
			"mdr"), Mende("men"), Irish_Middle("mga"), Mikmaq("mic"), Minangkabau(
			"min"), Uncoded_languages("mis"), Mon_Khmer_languages("mkh"), Malagasy(
			"mlg"), Maltese("mlt"), Manchu("mnc"), Manipuri("mni"), Manobo_languages(
			"mno"), Mohawk("moh"), Mongolian("mon"), Mossi("mos"), Multiple_languages(
			"mul"), Munda_languages("mun"), Creek("mus"), Mirandese("mwl"), Marwari(
			"mwr"), Mayan_languages("myn"), Erzya("myv"), Nahuatl("nah"), North_American_Indian_languages(
			"nai"), Neapolitan("nap"), Nauruan("nau"), Navajo("nav"), Southern_Ndebele(
			"nbl"), Northern_Ndebele("nde"), Ndonga("ndo"), Low_German("nds"), Nepali(
			"nep"), Nepal_Bhasa("new"), Nias("nia"), Niger_Kordofanian_languages(
			"nic"), Niuean("niu"), Norwegian_Nynorsk("nno"), Norwegian_Bokmal(
			"nob"), Nogai("nog"), Norse_Old("non"), Norwegian("nor"), NKo("nqo"), Northern_Sotho(
			"nso"), Nubia_languages("nub"), Classical_Newari("nwc"), Chichewa(
			"nya"), Nyamwezi("nym"), Nyankole("nyn"), Nyoro("nyo"), Nzima("nzi"), Occitan(
			"oci"), Ojibwa("oji"), Oriya("ori"), Oromo("orm"), Osage("osa"), Ossetian(
			"oss"), Turkish_Ottoman("ota"), Otomian_languages("oto"), Papuan_languages(
			"paa"), Pangasinan("pag"), Pahlavi("pal"), Pampanga("pam"), Punjabi(
			"pan"), Papiamento("pap"), Palauan("pau"), Persian_Old("peo"), Persian(
			"per"), Philippine_languages("phi"), Phoenician("phn"), Pali("pli"), Polish(
			"pol"), Pohnpeian("pon"), Portuguese("por"), Prakrit("pra"), Provencal_Old(
			"pro"), Pashto_language("pus"), Quechua("que"), Rajasthani("raj"), Rapanui(
			"rap"), Rarotongan("rar"), Romance_languages("roa"), Romansh("roh"), Romany(
			"rom"), Romanian("rum"), Rundi("run"), Aromanian("rup"), Russian(
			"rus"), Sandawe("sad"), Sango("sag"), Yakut("sah"), South_American_Indian_languages(
			"sai"), Salishan_languages("sal"), Samaritan_Aramaic("sam"), Sanskrit(
			"san"), Sasak("sas"), Santali("sat"), Sicilian("scn"), Scots("sco"), Selkup(
			"sel"), Semitic_languages("sem"), Irish_Old("sga"), Sign_languages(
			"sgn"), Shan("shn"), Sidamo("sid"), Sinhalese("sin"), Siouan_languages(
			"sio"), Sino_Tibetan_languages("sit"), Slavic_languages("sla"), Slovak(
			"slo"), Southern_Sami("sma"), Northern_Sami("sme"), Sami_languages(
			"smi"), Lule_Sami("smj"), Inari_Sami("smn"), Samoan("smo"), Skolt_Sami(
			"sms"), Shona("sna"), Sindhi("snd"), Soninke("snk"), Sogdian("sog"), Somali(
			"som"), Songhay_languages("son"), Southern_Sotho("sot"), Spanish(
			"spa"), Sardinian("srd"), Sranan_Tongo("srn"), Serbian("srp"), Serer(
			"srr"), Nilo_Saharan_languages("ssa"), Swati("ssw"), Sukuma("suk"), Sundanese(
			"sun"), Susu("sus"), Sumerian("sux"), Swahili("swa"), Swedish("swe"), Classical_Syriac(
			"syc"), Syriac("syr"), Tahitian("tah"), Tai_languages("tai"), Tamil(
			"tam"), Tatar("tat"), Telugu("tel"), Time("tem"), Tereno("ter"), Tetum(
			"tet"), Tajik("tgk"), Tagalog("tgl"), Thai("tha"), Tigre("tig"), Tigrinya(
			"tir"), Tiv("tiv"), Tokelau("tkl"), Klingon("tlh"), Tlingit("tli"), Tamashek(
			"tmh"), Tonga_Nyasa("tog"), Tonga_Togna_Islands("ton"), Tok_Pisin(
			"tpi"), Tsimshian("tsi"), Tswana("tsn"), Tsonga("tso"), Turkmen(
			"tuk"), Tumbuka("tum"), Tupian_languages("tup"), Turkish("tur"), Altaic_languages(
			"tut"), Tuvalu("tvl"), Twi("twi"), Tuvinian("tyv"), Udmurt("udm"), Ugaritic(
			"uga"), Uighur("uig"), Ukrainian("ukr"), Umbundu("umb"), Undetermined_language(
			"und"), Urdu("urd"), Uzbek("uzb"), Vai("vai"), Venda("ven"), Vietnamese(
			"vie"), Volapuk("vol"), Votic("vot"), Wakashan_languages("wak"), Wolaytta(
			"wol"), Waray_Waray("war"), Washo("was"), Welsh("wel"), Sorbian_languages(
			"wen"), Walloon("wln"), Wolof("wol"), Kalmyk("xal"), Xhosa("xho"), Yao(
			"yao"), Yapese("yap"), Yiddish("yid"), Yoruba("yor"), Yupik_languages(
			"ypk"), Zapotec("zap"), Blissymbols("zpl"), Zenaga("zen"), Zhuang(
			"zha"), Zande_languages("znd"), Zulu("zul"), Zuni("zun"), Zaza(
			"zza");

	private final String identifier;

	private Language(String identifier) {
		this.identifier = identifier;
	}

	@Override
	public final String toString() {
		return this.identifier;
	}

	private static final Map<String, Language> stringToEnum = new HashMap<String, Language>();

	static {
		for (Language l : EnumSet.allOf(Language.class)) {
			stringToEnum.put(l.toString(), l);
		}
	}

	public static Language getByValue(String value) {
		return stringToEnum.get(value);
	}
}
