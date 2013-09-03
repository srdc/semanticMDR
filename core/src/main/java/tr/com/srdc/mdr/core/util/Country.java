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
 *         Enumeration of ISO 3166-1 alpha-2 codes. Populated from <a
 *         href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">the Wikipedia
 *         page</a>.
 * 
 */
public enum Country {

	Andorra("AD"), United_Arab_Emirates("AE"), Afghanistan("AF"), Antigua_and_Barbuda(
			"AG"), Anguilla("AI"), Albania("AL"), Armenia("AM"), Angola("AO"), Antarctica(
			"AQ"), Argentina("AR"), American_Samoa("AS"), Austria("AT"), Australia(
			"AU"), Aruba("AW"), Aland_Islands("AX"), Azerbaijan("AZ"), Bosnia_and_Herzegovina(
			"BA"), Barbados("BB"), Bangladesh("BD"), Belgium("BE"), Burkina_Faso(
			"BF"), Bulgaria("BG"), Bahrain("BH"), Burundi("BI"), Benin("BJ"), Saint_Barthelemy(
			"BL"), Bermuda("BM"), Brunei_Darussalam("BN"), Bolivia_Plurinational_State_of(
			"BO"), Bonaire_Sint_Eustatius_and_Saba("BQ"), Brazil("BR"), Bahamas(
			"BS"), Bhutan("BT"), Bouvet_Island("BV"), Botswana("BW"), Belarus(
			"BY"), Belize("BZ"), Canada("CA"), Cocos_Keeling_Islands("CC"), Congo_the_Democratic_Republic_of_the(
			"CD"), Central_African_Republic("CF"), Congo("CG"), Switzerland(
			"CH"), Cote_dIvoire("CI"), Cook_Islands("CK"), Chile("CL"), Cameroon(
			"CM"), China("CN"), Colombia("CO"), Costa_Rica("CR"), Cuba("CU"), Cape_Verde(
			"CV"), Curacao("CW"), Christmas_Island("CX"), Cyprus("CY"), Czech_Republic(
			"CZ"), Germany("DE"), Djibouti("DJ"), Denmark("DK"), Dominica("DM"), Dominican_Republic(
			"DO"), Algeria("DZ"), Ecuador("EC"), Estonia("EE"), Egypt("EG"), Western_Sahara(
			"EH"), Eritrea("ER"), Spain("ES"), Ethiopia("ET"), Finland("FI"), Fiji(
			"FJ"), Falkland_Islands_Malvinas("FK"), Micronesia_Federated_States_of(
			"FM"), Faroe_Islands("FO"), France("FR"), Gabon("GA"), United_Kingdom(
			"GB"), Grenada("GD"), Georgia("GE"), French_Guiana("GF"), Guernsey(
			"GG"), Ghana("GH"), Gibraltar("GI"), Greenland("GL"), Gambia("GM"), Guinea(
			"GN"), Guadeloupe("GP"), Equatorial_Guinea("GQ"), Greece("GR"), South_Georgia_and_the_South_Sandwich_Islands(
			"GS"), Guatemala("GT"), Guam("GU"), Guinea_Bissau("GW"), Guyana(
			"GY"), Hong_Kong("HK"), Heard_Island_and_McDonald_Islands("HM"), Honduras(
			"HN"), Croatia("HR"), Haiti("HT"), Hungary("HU"), Indonesia("ID"), Ireland(
			"IE"), Israel("IL"), Isle_of_Man("IM"), India("IN"), British_Indian_Ocean_Territory(
			"IO"), Iraq("IQ"), Iran_Islamic_Republic_of("IR"), Iceland("IS"), Italy(
			"IT"), Jersey("JE"), Jamaica("JM"), Jordan("JO"), Japan("JP"), Kenya(
			"KE"), Kyrgyzstan("KG"), Cambodia("KH"), Kiribati("KI"), Comoros(
			"KM"), Saint_Kitts_and_Nevis("KN"), Korea_Democratic_Peoples_Republic_of(
			"KP"), Korea_Republic_of("KR"), Kuwait("KW"), Cayman_Islands("KY"), Kazakhstan(
			"KZ"), Lao_Peoples_Democratic_Republic("LA"), Lebanon("LB"), Saint_Lucia(
			"LC"), Liechtenstein("LI"), Sri_Lanka("LK"), Liberia("LR"), Lesotho(
			"LS"), Lithuania("LT"), Luxembourg("LU"), Latvia("LV"), Libya("LY"), Morocco(
			"MA"), Monaco("MC"), Moldova_Republic_of("MD"), Montenegro("ME"), Saint_Martin_French_part(
			"MF"), Madagascar("MG"), Marshall_Islands("MH"), Macedonia_the_former_Yugoslav_Republic_of(
			"MK"), Mali("ML"), Myanmar("MM"), Mongolia("MN"), Macao("MO"), Northern_Mariana_Islands(
			"MP"), Martinique("MQ"), Mauritania("MR"), Montserrat("MS"), Malta(
			"MT"), Mauritius("MU"), Maldives("MV"), Malawi("MW"), Mexico("MX"), Malaysia(
			"MY"), Mozambique("MZ"), Namibia("NA"), New_Caledonia("NC"), Niger(
			"NE"), Norfolk_Island("NF"), Nigeria("NG"), Nicaragua("NI"), Netherlands(
			"NL"), Norway("NO"), Nepal("NP"), Nauru("NR"), Niue("NU"), New_Zealand(
			"NZ"), Oman("OM"), Panama("PA"), Peru("PE"), French_Polynesia("PF"), Papua_New_Guinea(
			"PG"), Philippines("PH"), Pakistan("PK"), Poland("PL"), Saint_Pierre_and_Miquelon(
			"PM"), Pitcairn("PN"), Puerto_Rico("PR"), Palestinian_Territory_Occupied(
			"PS"), Portugal("PT"), Palau("PW"), Paraguay("PY"), Qatar("QA"), Reunion(
			"RE"), Romania("RO"), Serbia("RS"), Russian_Federation("RU"), Rwanda(
			"RW"), Saudi_Arabia("SA"), Solomon_Islands("SB"), Seychelles("SC"), Sudan(
			"SD"), Sweden("SE"), Singapore("SG"), Saint_Helena_Ascension_and_Tristan_da_Cunha(
			"SH"), Slovenia("SI"), Svalbard_and_Jan_Mayen("SJ"), Slovakia("SK"), Sierra_Leone(
			"SL"), San_Marino("SM"), Senegal("SN"), Somalia("SO"), Suriname(
			"SR"), South_Sudan("SS"), Sao_Tome_and_Principe("ST"), El_Salvador(
			"SV"), Sint_Maarten_Dutch_part("SX"), Syrian_Arab_Republic("SY"), Swaziland(
			"SZ"), Turks_and_Caicos_Islands("TC"), Chad("TD"), French_Southern_Territories(
			"TF"), Togo("TG"), Thailand("TH"), Tajikistan("TJ"), Tokelau("TK"), Timor_Leste(
			"TL"), Turkmenistan("TM"), Tunisia("TN"), Tonga("TO"), Turkey("TR"), Trinidad_and_Tobago(
			"TT"), Tuvalu("TV"), Taiwan_Province_of_China("TW"), Tanzania_United_Republic_of(
			"TZ"), Ukraine("UA"), Uganda("UG"), United_States_Minor_Outlying_Islands(
			"UM"), United_States("US"), Uruguay("UY"), Uzbekistan("UZ"), Holy_See_Vatican_City_State(
			"VA"), Saint_Vincent_and_the_Grenadines("VC"), Venezuela_Bolivarian_Republic_of(
			"VE"), Virgin_Islands_British("VG"), Virgin_Islands_US("VI"), Viet_Nam(
			"VN"), Vanuatu("VU"), Wallis_and_Futuna("WF"), Samoa("WS"), Yemen(
			"YE"), Mayotte("YT"), South_Africa("ZA"), Zambia("ZM"), Zimbabwe(
			"ZW");

	private final String identifier;

	private Country(String identifier) {
		this.identifier = identifier;
	}

	@Override
	public final String toString() {
		return this.identifier;
	}

	private static final Map<String, Country> stringToEnum = new HashMap<String, Country>();

	static {
		for (Country c : EnumSet.allOf(Country.class)) {
			stringToEnum.put(c.toString(), c);
		}
	}

	public static Country getByValue(String value) {
		return stringToEnum.get(value);
	}

}
