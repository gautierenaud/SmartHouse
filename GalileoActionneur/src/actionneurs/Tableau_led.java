package actionneurs;

public class Tableau_led {

	//======================================================================
	//==================== VARIABLES =======================================
	//======================================================================
	
	private String mon_tableau[];
	
	private String tab_rempli_7[];
	private String tab_rempli_6[];
	
	//======================================================================
	//==================== CONSTRUCTEUR ====================================
	//======================================================================
	
	/**
	 * Constructeur.
	 */
	public Tableau_led() {
		int i;
		this.mon_tableau = new String[21];
		for (i=0; i<21; i++) {
			this.mon_tableau[i] = "";
			for (int j=0; j<21; j++) {
				this.mon_tableau[i] += '0';
			}
		}
		this.tab_rempli_7 = get_tableau_rempli(7, 21);
		this.tab_rempli_6 = get_tableau_rempli(6, 21);

	}
	
	//======================================================================
	//==================== Methodes Publiques ==============================
	//======================================================================
	
	//****************** AFFICHAGE ET CONVERSION ********************
	
	/**
	 * @return	Le tableau 21*21 en string avec retour à la ligne.
	 */
	public String toString() {
		String string_tab = "";
		for (int ligne=0; ligne <21; ligne++) {
			string_tab += mon_tableau[ligne] + "\n";
		}
		return string_tab;
	}
	
	/**
	 * @return	Le tableau sous forme d'octet => 21*21 = 441 octets au total.
	 */
	public byte[] toByte() {
		byte mon_tab_bytes[] = new byte[441] ;
		for (int ligne=0; ligne<21; ligne++) {
			for (int colonne=0; colonne<21; colonne++) {
				mon_tab_bytes[(ligne*21)+colonne] = (byte)mon_tableau[ligne].charAt(colonne);
			}
		}
		return mon_tab_bytes;
	}
	
	//****************************************************************
	//****************** Dessin **************************************
	//****************************************************************
	
	/**
	 * Placer un dessin à un endroit du tableau, repère dont l'origine est en haut à gauche du tab,
	 * Conseil : Si on veut nettoyer une zone, mettre tous les bits du dessin à 1 
	 * puis nettoyer a true.
	 * @param mon_dessin	Un dessin qlconque.
	 * @param pos_x		Le 0 commence à gauche.
	 * @param pos_y		Le 0 commence en haut.
	 * @param nettoyer	False quand on veut dessiner, True si on veut Supprimer notre ancien dessin.
	 */
	public void placer_dessin( String[] mon_dessin, int pos_x, int pos_y, boolean nettoyer ) {
		int nb_lignes = mon_dessin.length;
		int nb_colonnes = mon_dessin[0].length();
		int x,y;

		/** On peut dessiner **/
		for (int ligne=0; ligne<nb_lignes; ligne++) {
			for (int colonne=0; colonne<nb_colonnes; colonne++) {
				x = ligne + pos_x;
				y = colonne + pos_y;
				//System.out.println("x("+x+") y("+y+")");
				// Verifier que la ligne existe
				if ( (x>=0) && (x<=20) ) {
					// verifier que la colonne existe
					if ( (y>=0) && (y<=20) ) {
						if ( mon_dessin[ligne].charAt(colonne)=='1' ) {
							if (!nettoyer) {
								remplacer_value(x, y, 1);
							} else {
								remplacer_value(x, y, 0);
							}
						}
					}
				}
			}
		}
		/** Fin du dessin **/
	}
	
	/**
	 * Afficher du texte à une position x, y.
	 * @param mon_texte
	 * @param x
	 * @param y
	 */
	public void set_text( String mon_texte, int x, int y ) {
		String[] mon_texte_pixel = Lettres_pixels.stringToTab(mon_texte, false);
		
		// Nettoyer la zone où on mettra le texte
		placer_dessin(this.tab_rempli_7, x, 0, true);
		System.out.println("**** test de la conversion en texte");
		Lettres_pixels.afficher_tableau_string(mon_texte_pixel);
		System.out.println("**** fin test de la conversion en texte");
		
		// Placer le texte
		placer_dessin(mon_texte_pixel, x, y, false);
	}
	
	/**
	 * Affiche l'heure sur le tableau, sur un espace de 6*21.
	 * @param heure		De 0 à 23.
	 * @param minute	De 0 à 59.
	 * @param ligne	La ligne de depart (conseille 11).
	 */
	public void afficher_heure(int heures, int minutes, int ligne_depart) {
		String string_heure;
		String[] string_tab_heure;
		
		//System.out.println("Affichage du tableau avant : \n"+toString()+"\n");
		
		// Nettoyer l'espace où on affichera l'heure
		placer_dessin(this.tab_rempli_6, ligne_depart, 0, true);
		
		//System.out.println("Affichage du tableau apres nettoyage : \n"+toString()+"\n");
		
		// Recuperer le string pour l'heure
		string_heure = retourner_format_heure(heures, minutes);
		// Recuperer le tableau de string associe
		string_tab_heure = Lettres_pixels.stringToTab(string_heure, true);
		//Lettres_pixels.afficher_tableau_string(string_tab_heure);
		
		// L'afficher = modifier le tableau
		placer_dessin(string_tab_heure, ligne_depart, 0, false);
	}
	
	//======================================================================
	//==================== Methodes privees ================================
	//======================================================================
	
	

	//======================================================================
	//==================== Methodes Privees ================================
	//======================================================================
	
	//********************* Pour le dessin **********************

	/**
	 * Placer un char au bon emplacement dans le tableau.
	 * @param x		De 0 à 20.
	 * @param y		De 0 à 20.
	 * @param my_value	'0' ou '1'.
	 */
	private void remplacer_value(int x, int y, int my_value) {
		//System.out.println("Avant - Remplacer value : x("+x+") et y("+y+") et longueur tab("+mon_tableau[x].length()+")");
		//System.out.println("TEST STRING : "+this.mon_tableau[x]);
		String aux_ligne = "";
		// Cas de debut de ligne
		if ( y==0 ) {
			aux_ligne = ""+my_value + this.mon_tableau[x].substring(1, 21);
			this.mon_tableau[x] = aux_ligne;
		// Cas de fin de ligne
		} else if ( y==20 ) {
			this.mon_tableau[x] = ""+this.mon_tableau[x].substring(0, 20)+my_value;
		// Cas de 2e case
		} else if ( y==1 ) {
			this.mon_tableau[x] = ""+this.mon_tableau[x].charAt(0) + my_value
					  				+ this.mon_tableau[x].substring(2, 21);
		// Cas d'avant derniere case
		} else if ( y==19 ) {
			this.mon_tableau[x] = ""+this.mon_tableau[x].substring(0, 19) + my_value
		  							+ this.mon_tableau[x].charAt(20);
		// Cas normal
		} else {
			this.mon_tableau[x] = ""+this.mon_tableau[x].substring(0, y) + my_value
									+ this.mon_tableau[x].substring(y+1, 21);
		}
		//System.out.println("pour x("+x+") et value("+my_value+") "+this.mon_tableau[x]);
		//System.out.println("Après - Remplacer value : x("+x+") et y("+y+") et longueur tab("+mon_tableau[x].length()+")");
	}

	/**
	 * Retourne un tableau de string rempli de 1 => Utilisé pour le nettoyage.
	 * @param nb_lignes
	 * @param nb_colonnes
	 * @return	Le tableau de string avec des char 1.
	 */
	private String[] get_tableau_rempli(int nb_lignes, int nb_colonnes) {
		String[] mon_string = new String[nb_lignes];
		for ( int ligne=0; ligne<nb_lignes; ligne++ ) {
			mon_string[ligne] = "";
			for (int colonne=0; colonne<nb_colonnes; colonne++) {
				mon_string[ligne] += "1";
			}
		}
		//System.out.println("********** Mon tableau nettoyage ***********");
		//Lettres_pixels.afficher_tableau_string(mon_string);
		//System.out.println("********************************************");
		return mon_string;
	}

	
	//********************* Pour la conversion **********************
	
	/**
	 * Retour une ligne en 3 octets
	 * @param n_ligne	De 0 à 20.
	 * @return	Un tableau de 3 octets.
	 */
	private byte[] convert_ligne_to_bytes( int n_ligne ) {
		byte ma_ligne_bytes[] = new byte[3];
		
		// J'ai un string avec 21 charac.
		String string_byte_0 = mon_tableau[n_ligne].substring(0, 7);
		String string_byte_1 = mon_tableau[n_ligne].substring(8, 15);
		String string_byte_2 = mon_tableau[n_ligne].substring(16, 20)+"000";
		
		System.out.println(" convert_ligne : "	+string_byte_0
												+string_byte_1
												+string_byte_2);	
		
		ma_ligne_bytes[0] = Byte.parseByte(string_byte_0, 2);
		ma_ligne_bytes[1] = Byte.parseByte(string_byte_1, 2);
		ma_ligne_bytes[2] = Byte.parseByte(string_byte_2, 2);
		
		return ma_ligne_bytes;
	}
	
	
	/**
	 * Convertir un tableau de 3 bytes en string de 21 characteres.
	 * @param mes_bytes
	 * @return Un string de 21 char.
	 */
	private String convert_bytes_to_line( byte[] mes_bytes ) {
		String mon_string = null;
		String string_actuel = "";
		if (mes_bytes.length == 3) {
			mon_string = "";
			for (int i=0; i<3; i++) {
				if (mes_bytes.length == 3){
					string_actuel = String.format("%8s", Integer.toBinaryString(mes_bytes[i] & 0xFF))
																.replace(' ', '0');
					//System.out.println("convert byte line : "+ string_actuel);
					mon_string += string_actuel;
				}
			}
		}
		//System.out.println("Taille du string : "+mon_string.length());
		mon_string = mon_string.substring(1, 21);
		return mon_string;
	}
	
	
	/**
	 * Convertir 21*3 bytes en String pour pouvoir l'afficher.
	 * @param mon_tableau_byte
	 * @return	Un string tableau 21*21.
	 */
	private String convert_bytes_to_string( byte[] mon_tableau_byte) {
		String mon_tableau_string = null;
		byte[] my_3_bytes = new byte[3];
		if (mon_tableau_byte.length == (21*3)) {
			mon_tableau_string = "";
			for ( int ligne=0; ligne<21; ligne++) {
				my_3_bytes[0] = mon_tableau_byte[ligne*3];
				my_3_bytes[1] = mon_tableau_byte[ligne*3+1];
				my_3_bytes[2] = mon_tableau_byte[ligne*3+2];
				mon_tableau_string += convert_bytes_to_line(my_3_bytes)+"\n";
			}
		}
		return mon_tableau_string;
	}
	
	
	/**
	 * Convertir une heure en format string.
	 * @param heures
	 * @param minutes
	 * @return
	 */
	private String retourner_format_heure(int heures, int minutes) {
		String mon_heure = "";
		if (heures>=0 && heures <24 && minutes>=0 && minutes <60) {
			// Verif des parametres OK
			if (heures < 10) {
				// Il faudra ajouter un 0 puis le chiffre
				mon_heure += "0"+heures;
			} else {
				mon_heure += heures;
			}
			// Ajouter le ':'
			mon_heure +=":";
			if (minutes <10 ) {
				// Il faudra ajouter le 0 puis le chiffre
				mon_heure += "0"+minutes;
			} else {
				mon_heure += minutes;
			}
		} else {
			mon_heure += "99:99";
		}
		return mon_heure;
	}
	
	
	//======================================================================
	//==================== MAIN TEST =======================================
	//======================================================================
	
	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tableau_led mon_tableau = new Tableau_led();
		System.out.println(mon_tableau.toString());
		System.out.println("ligne 0 : "+mon_tableau.mon_tableau[0]);
		
		System.out.println("Test pour l'heure");
		mon_tableau.afficher_heure(12, 8, 2);	// 12:08 a partir de la ligne 2
		System.out.println(mon_tableau.toString());
		
		System.out.println("Test pour le texte");
		mon_tableau.set_text("jason est fort", 10, 0);
		System.out.println(mon_tableau.toString());
	}
*/
}
