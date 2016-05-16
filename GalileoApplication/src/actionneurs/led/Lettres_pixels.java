package actionneurs.led;
public class Lettres_pixels {
	
	/**
	 * Retourner la tableau de pixel correspondant au charactere.
	 * @param mon_char	Le character recherché.
	 * @param mode_chiffres_64	True si on veut que les chiffre ou le deux points soit en 6*4 => pour l'heure.
	 * @return
	 */
	public static String[] getLettre(char mon_char, boolean mode_6) {
		String[] mon_char_tab = null;
		switch(mon_char) {
			case ' ':	if (! mode_6){ mon_char_tab = le_espace_7; } else { mon_char_tab = le_espace_7; }
				break;
			case 'a':	mon_char_tab = le_a;
				break;
			case 'b':	mon_char_tab = le_b;
				break;
			case 'c':	mon_char_tab = le_c;
				break;
			case 'd':	mon_char_tab = le_d;
				break;
			case 'e':	mon_char_tab = le_e;
				break;
			case 'f':	mon_char_tab = le_f;
				break;
			case 'g':	mon_char_tab = le_g;
				break;
			case 'h':	mon_char_tab = le_h;
				break;
			case 'i':	mon_char_tab = le_i;
				break;
			case 'j':	mon_char_tab = le_j;
				break;
			case 'k':	mon_char_tab = le_k;
				break;
			case 'l':	mon_char_tab = le_l;
				break;
			case 'm':	mon_char_tab = le_m;
				break;
			case 'n':	mon_char_tab = le_n;
				break;
			case 'o':	mon_char_tab = le_o;
				break;
			case 'p':	mon_char_tab = le_p;
				break;
			case 'q':	mon_char_tab = le_q;
				break;
			case 'r':	mon_char_tab = le_r;
				break;
			case 's':	mon_char_tab = le_s;
				break;
			case 't':	mon_char_tab = le_t;
				break;
			case 'u':	mon_char_tab = le_u;
				break;
			case 'v':	mon_char_tab = le_v;
				break;
			case 'w':	mon_char_tab = le_w;
				break;
			case 'x':	mon_char_tab = le_x;
				break;
			case 'y':	mon_char_tab = le_y;
				break;
			case 'z':	mon_char_tab = le_z;
				break;
			case '0':	if (! mode_6){ mon_char_tab = le_0; } else { mon_char_tab = le_0_64; }
				break;
			case '1':	if (! mode_6){ mon_char_tab = le_1; } else { mon_char_tab = le_1_64; }
				break;
			case '2':	if (! mode_6){ mon_char_tab = le_2; } else { mon_char_tab = le_2_64; }
				break;
			case '3':	if (! mode_6){ mon_char_tab = le_3; } else { mon_char_tab = le_3_64; }
				break;
			case '4':	if (! mode_6){ mon_char_tab = le_4; } else { mon_char_tab = le_4_64; }
				break;
			case '5':	if (! mode_6){ mon_char_tab = le_5; } else { mon_char_tab = le_5_64; }
				break;
			case '6':	if (! mode_6){ mon_char_tab = le_6; } else { mon_char_tab = le_6_64; }
				break;
			case '7':	if (! mode_6){ mon_char_tab = le_7; } else { mon_char_tab = le_7_64; }
				break;
			case '8':	if (! mode_6){ mon_char_tab = le_8; } else { mon_char_tab = le_8_64; }
				break;
			case '9':	if (! mode_6){ mon_char_tab = le_9; } else { mon_char_tab = le_9_64; }
				break;
			case '.':	mon_char_tab = le_point;
				break;
			case '?':	mon_char_tab = le_point_integ;
				break;
			case '!':	mon_char_tab = le_point_excla;
				break;
			case '\'':	mon_char_tab = le_slash;
				break;
			case ':':	if ( ! mode_6) { mon_char_tab = le_2points_74; } else { mon_char_tab = le_2points_64; }
				break;
			case ',':	mon_char_tab = le_virgule;
				break;
			case '[':	mon_char_tab = le_croch1;
				break;
			case ']':	mon_char_tab = le_croch2;
				break;
			case '<':	mon_char_tab = le_inf;
				break;
			case '>':	mon_char_tab = le_sup;
				break;
			case '+':	mon_char_tab = le_plus;
				break;
			case '-':	mon_char_tab = le_moins;
				break;
			case '€':	mon_char_tab = le_euro;
				break;
			default :	mon_char_tab = le_espace_7;
				
		}
		return mon_char_tab;
	}

	/**
	 * Retourner le tableau de pixel correspondant au String.
	 * @param mon_string	La chaine a convertir.
	 * @param mode_6	True si on veut que les chiffres soient de hauteur 6.
	 * @return
	 */
	public static String[] stringToTab( String mon_string, boolean mode_6 ) {
		String[] mon_tab = null;
		String[] string_char_actuel = null;
		int hauteur = 7;
		int longueur_string = mon_string.length();
		int ligne;
		if (longueur_string != 0) {
			if (mode_6) { hauteur = 6; }
			mon_tab = new String[hauteur];
			for (int i=0; i<hauteur; i++) {
				mon_tab[i]="";
			}
			// Pour chaque string du character
			for ( int pos_char=0; pos_char<longueur_string; pos_char++ ) {
				// On recupere son tableau de pixel
				string_char_actuel = getLettre(mon_string.charAt(pos_char), mode_6);
				for (ligne=0; ligne<hauteur; ligne++) {
					// On ajoute 
					mon_tab[ligne] += string_char_actuel[ligne] + "0";
				}
			}
		}
		return mon_tab;
	}
	
	/**
	 * Afficher un tableau de string.
	 * @param mon_tab	Le tableau de string.
	 */
	public static void afficher_tableau_string( String[] mon_tab ) {
		if (mon_tab.length != 0) {
			if (mon_tab[0].length() != 0) {
				System.out.println("afficher_tableau_string - ***** Debut affichage du tableau de string *****\n");
				for (int ligne=0; ligne<mon_tab.length; ligne++) {
					// Afficher chaque ligne du tableau de string
					System.out.println(mon_tab[ligne]);
				}
				System.out.println("\nafficher_tableau_string - ***** Fin affichage du tableau de string *****\n");
			} else {
				System.out.println("afficher_tableau_string - String dans le tableau vide");
			}
		} else {
			System.out.println("afficher_tableau_string - Tableau vide");
		}
	}

	
	//====================================================================
	//============= Différentes lettres et symboles ======================
	
	static String[] le_vide = { 	
			"00000",
			"00000",
			"00000",
			"00000",
			"00000",
			"00000",
			"00000"	};
	
	static String[] le_espace_7 = {
			"00",
			"00",
			"00",
			"00",
			"00",
			"00",
			"00" 		};
	static String[] le_espace_6 = {
			"00",
			"00",
			"00",
			"00",
			"00",
			"00"				};
	
	// Lettres
	static String[] le_a = { 	
			"01110",
			"10001",
			"10001",
			"11111",
			"10001",
			"10001",
			"10001" 	};
	static String[] le_b = { 	
			"11110",
			"10001",
			"10001",
			"11110",
			"10001",
			"10001",
			"11110"		};
	static String[] le_c = { 	
			"01110",
			"10001",
			"10000",
			"10000",
			"10000",
			"10001",
			"01110"		};
	static String[] le_d = { 	
			"11110",
			"10001",
			"10001",
			"10001",
			"10001",
			"10001",
			"11110"		};
	static String[] le_e = { 	
			"11111",
			"10000",
			"10000",
			"11110",
			"10000",
			"10000",
			"11111"		};
	static String[] le_f = { 	
			"11111",
			"10000",
			"10000",
			"11110",
			"10000",
			"10000",
			"10000"		};
	static String[] le_g = { 	
			"01110",
			"10001",
			"10001",
			"10001",
			"10001",
			"10001",
			"01110"		};
	static String[] le_h = { 	
			"11110",
			"10001",
			"10001",
			"11110",
			"10000",
			"10000",
			"10000"		};
	static String[] le_i = { 	
			"01110",
			"00100",
			"00100",
			"00100",
			"00100",
			"00100",
			"01110"		};
	static String[] le_j = { 	
			"00111",
			"00010",
			"00010",
			"00010",
			"00010",
			"10010",
			"01100"		};
	static String[] le_k = { 	
			"10001",
			"10010",
			"10100",
			"11000",
			"10100",
			"10010",
			"10001"		};
	static String[] le_l = { 	
			"10000",
			"10000",
			"10000",
			"10000",
			"10000",
			"10000",
			"11111"		};
	static String[] le_m = { 	
			"10001",
			"11011",
			"10101",
			"10001",
			"10001",
			"10001",
			"10000"		};
	static String[] le_n = { 	
			"10001",
			"10001",
			"11001",
			"10101",
			"10011",
			"10001",
			"10001"		};
	static String[] le_o = { 	
			"01110",
			"10001",
			"10001",
			"10001",
			"10001",
			"10001",
			"01110"		};
	static String[] le_p = { 	
			"11110",
			"10001",
			"10001",
			"11110",
			"10000",
			"10000",
			"10000"		};
	static String[] le_q = { 	
			"01110",
			"10001",
			"10001",
			"10001",
			"10101",
			"10010",
			"01101"		};
	static String[] le_r = { 	
			"11110",
			"10001",
			"10001",
			"11110",
			"10100",
			"10010",
			"10001"		};
	static String[] le_s = { 	
			"01111",
			"10000",
			"10000",
			"01110",
			"00001",
			"00001",
			"11110"		};
	static String[] le_t = { 	
			"11111",
			"00100",
			"00100",
			"00100",
			"00100",
			"00100",
			"00100"		};
	static String[] le_u = { 	
			"10001",
			"10001",
			"10001",
			"10001",
			"10001",
			"10001",
			"01110"		};
	static String[] le_v = { 	
			"10001",
			"10001",
			"10001",
			"10001",
			"01010",
			"01010",
			"00100"		};
	static String[] le_w = { 	
			"10001",
			"10001",
			"10001",
			"10101",
			"10101",
			"10101",
			"01010"		};
	static String[] le_x = { 	
			"10001",
			"10001",
			"01010",
			"00100",
			"01010",
			"10001",
			"10001"		};
	static String[] le_y = { 	
			"10001",
			"10001",
			"01010",
			"00100",
			"00100",
			"00100",
			"00100"		};
	static String[] le_z = { 	
			"11111",
			"00001",
			"00010",
			"00100",
			"01000",
			"10000",
			"11111"		};
	
	// Chiffres => En 7*5
			static String[] le_0 = { 	
					"01110",
					"10001",
					"10001",
					"10101",
					"10001",
					"10001",
					"01110"		};
			static String[] le_1 = { 	
					"00100",
					"01100",
					"00100",
					"00100",
					"00100",
					"00100",
					"01110"		};
			static String[] le_2 = { 	
					"01110",
					"10001",
					"00001",
					"00010",
					"00100",
					"01000",
					"11111"		};
			static String[] le_3 = { 	
					"11111",
					"00010",
					"00100",
					"00010",
					"00001",
					"10001",
					"01110"		};
			static String[] le_4 = { 	
					"00010",
					"00110",
					"01010",
					"10010",
					"11111",
					"00010",
					"00010"		};
			static String[] le_5 = { 	
					"11111",
					"10000",
					"11110",
					"00001",
					"00001",
					"10001",
					"01110"		};
			static String[] le_6 = { 	
					"00110",
					"01000",
					"10000",
					"11110",
					"10001",
					"10001",
					"01110"		};
			static String[] le_7 = { 	
					"11111",
					"00001",
					"00010",
					"00100",
					"01000",
					"01000"	};
			static String[] le_8 = { 	
					"01110",
					"10001",
					"10001",
					"01110",
					"10001",
					"10001",
					"01110"		};
			static String[] le_9 = { 	
					"01110",
					"10001",
					"10001",
					"01111",
					"00001",
					"00010",
					"01100"};
	
	// Chiffres => En 6*4
	static String[] le_0_64 = { 	
			"0110",
			"1001",
			"1001",
			"1001",
			"1001",
			"0110",	};
	static String[] le_1_64 = { 	
			"0100",
			"1100",
			"0100",
			"0100",
			"0100",
			"1110"	};
	static String[] le_2_64 = { 	
			"0110",
			"1001",
			"0001",
			"0010",
			"0100",
			"1111"	};
	static String[] le_3_64 = { 	
			"1111",
			"0010",
			"0100",
			"0010",
			"1001",
			"0110"	};
	static String[] le_4_64 = { 	
			"0010",
			"0010",
			"0110",
			"1010",
			"1111",
			"0010"	};
	static String[] le_5_64 = { 	
			"1111",
			"1000",
			"1110",
			"0001",
			"1001",
			"0110"	};
	static String[] le_6_64 = { 	
			"0011",
			"0100",
			"1000",
			"1110",
			"1001",
			"0110"	};
	static String[] le_7_64 = { 	
			"1111",
			"0001",
			"0010",
			"0100",
			"1000",
			"1000"	};
	static String[] le_8_64 = { 	
			"0110",
			"1001",
			"0110",
			"1001",
			"1001",
			"0110"		};
	static String[] le_9_64 = { 	
			"0110",
			"1001",
			"1001",
			"0111",
			"0001",
			"0110"		};
	
	// Symboles => !!! Attention certains sont en 7*4 d'autres en 6*4
	static String[] le_point = { 	
			"0000",
			"0000",
			"0000",
			"0000",
			"0000",
			"0110",
			"0110"		};
	static String[] le_point_excla = { 	
			"0110",
			"0110",
			"0110",
			"0110",
			"0000",
			"0110",
			"0110"		};
	static String[] le_point_integ = { 	
			"0110",
			"1001",
			"0011",
			"0110",
			"0000",
			"0110",
			"0110"		};
	static String[] le_slash = { 	
			"00001",
			"00001",
			"00010",
			"00100",
			"01000",
			"10000",
			"10000"		};
	static String[] le_2points_64 = {
			"0",
			"1",
			"0",
			"0",
			"1",
			"0",	};
	static String[] le_2points_74 = {
			"0000",
			"0110",
			"0110",
			"0000",
			"0000",
			"0110",
			"0110",	};
	static String[] le_virgule = { 	
			"0000",
			"0000",
			"0000",
			"0110",
			"0110",
			"0100",
			"0010",	};
	static String[] le_apostrophe = { 	
			"0110",
			"0110",
			"0010",
			"0100",
			"0000",
			"0000",
			"0000",	};
	static String[] le_croch1 = { 	
			"01110",
			"01000",
			"01000",
			"01000",
			"01000",
			"01000",
			"01110"		};
	static String[] le_croch2 = { 	
			"01110",
			"00010",
			"00010",
			"00010",
			"00010",
			"00010",
			"01110"		};
	static String[] le_inf = { 	
			"00010",
			"00100",
			"01000",
			"10000",
			"01000",
			"00100",
			"00010"		};
	static String[] le_sup = { 	
			"10000",
			"01000",
			"00100",
			"00010",
			"00100",
			"01000",
			"10000"		};
	static String[] le_plus = { 	
			"00000",
			"00100",
			"00100",
			"11111",
			"00100",
			"00100",
			"00000"		};
	static String[] le_moins = { 	
			"00000",
			"00000",
			"00000",
			"11111",
			"00000",
			"00000",
			"00000"		};
	static String[] le_euro = { 	
			"00110",
			"01001",
			"11100",
			"01000",
			"11100",
			"01001",
			"00110"		};
	
	}
