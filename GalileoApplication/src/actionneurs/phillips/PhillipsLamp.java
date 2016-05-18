package actionneurs.phillips;
import java.io.*;
import java.net.*;
import java.util.*;
import org.json.*;

// Les reponses sont en .json , je fais des fonctions pour eviter d'avoir a les interpreter
// Dans ce Programme, on ne se contente que d'une seule lampe.
// Bien sûr il sera possible de le changer pour faire des groupes/n'affecter que certaines lampes etc...


public class PhillipsLamp implements Runnable {

	
	public PhillipsLamp() 
	{
		add_IP = null;
		mon_ID_user = "";
		url_racine = null;
		url_user = null;
		finish = false;
	}
	
//=================================================================================
//================================ VARIABLES ======================================
//=================================================================================
	String colorType;
	String add_IP;
	String mon_ID_user;
	String url_racine;	// avant la connection
	String url_user;	// apres la connection
	// Constantes
	int post=0, get=1, put=2;
	boolean finish;
//=================================================================================
//====================== FONCTIONS pour les lampes ================================
//=================================================================================

	/**
	 * Allumer et eteindre la lampe n°1.
	 * @param allumee
	 */
	public void set_etat_lampe_1(boolean allumee) 
	{
		set_etat_lampe(allumee, 1);
	}
	
	public void close()
	{
		finish = true;
	}
	/**
	 *	Faire clignoter la lampe n°1 en rouge pendant 10 secondes.
	 */
	public void clignoter_rouge_auto(int temps_secondes) 
	{
		clignoter(240, 10, 15, 300, temps_secondes, 1);
		changer_couleur(245, 10, 0, 240, 240, 1);
	}

	/**
	 * Mettre une transition de couleur, de rouge-orange faible luminosité à forte.
	 * @param temps_transition_secondes		Le temps de la transition entre fort et faible (conseil 10 000 ~ 10mn).
	 */
	public void set_ambiance_reveil_matin(int temps_transition_secondes) throws Exception {
		transition_rgb(	238, 30, 0, 5, 250, 
						238, 90, 10, 250, 240, 
						temps_transition_secondes*1000, 1);
	}

	/**
	 * Mettre une ambiance de couleur : tons rouge-orange pour l'optimisme.
	 * @param temps_transition_secondes (temps conseillé, >100) sinon la transition se fait assez mal
	 * @param temps_total_secondes 	Duree totale de ce mode.
	 */
	public void set_ambiance_optimism( int temps_transition_secondes, int temps_total_secondes ) throws Exception {
		long temps_depart_ms = System.currentTimeMillis();
		while (!finish  && temps_total_secondes > (System.currentTimeMillis()-temps_depart_ms)*1000) {
			transition_rgb(	238, 64, 0, 240, 240, 
							238, 201, 0, 240, 240, 
							temps_transition_secondes*1000, 1);
			transition_rgb(	238, 201, 0, 240, 240, 
							238, 64, 0, 240, 240, 
							temps_transition_secondes*1000, 1);
		}
	}
	
	/**
	 * Mettre une ambiance de couleur : tons bleu-vert nature pour le repos et la croissance.
	 * @param temps_transition_secondes (temps conseillé, >100) sinon la transition se fait assez mal
	 * @param temps_total_secondes 	Duree totale de ce mode.
	 */
	public void set_ambiance_nature( int temps_transition_secondes, int temps_total_secondes ) throws Exception {
		long temps_depart_ms = System.currentTimeMillis();
		while (!finish && temps_total_secondes > (System.currentTimeMillis()-temps_depart_ms)*1000) {
			// De ForestGreen à DarkSeaGreen4
			transition_rgb(	34, 139, 34, 240, 250, 
							105, 139, 34, 240, 250, 
							temps_transition_secondes*1000, 1);
			transition_rgb(	105, 139, 34, 240, 250, 
							105, 139, 105, 240, 250, 
							temps_transition_secondes*1000, 1);
			transition_rgb(	105, 139, 105, 240, 250, 
							105, 169, 105, 240, 250, 
							temps_transition_secondes*1000, 1);
			// De DarkSeaGreen4 à aquamarine4
			transition_rgb(	105, 169, 105, 240, 250, 
							105, 169, 116, 240, 250, 
							temps_transition_secondes*1000, 1);
			transition_rgb(	105, 169, 116, 240, 250, 
							69, 169, 116, 240, 250, 
							temps_transition_secondes*1000, 1);
			// De aquamarine4 à sky4
			transition_rgb(	69, 169, 116, 240, 250, 
							69, 112, 116, 240, 250, 
							temps_transition_secondes*1000, 1);
			transition_rgb(	69, 112, 116, 240, 250, 
							74, 112, 116, 240, 250, 
							temps_transition_secondes*1000, 1);
			transition_rgb(	74, 112, 116, 240, 250, 
							74, 112, 139, 240, 250, 
							temps_transition_secondes*1000, 1);
			// De sky4 à vert foncé
			transition_rgb(	74, 112, 139, 240, 250, 
							74, 112, 10 , 240, 250,
							temps_transition_secondes*1000, 1);
			transition_rgb(	74, 139, 10, 240, 250, 
							10, 139, 10 , 240, 250,
							temps_transition_secondes*1000, 1);
			transition_rgb(	10, 139, 34, 240, 250, 
							10, 110, 10, 240, 250,  
							temps_transition_secondes*1000, 1);
			// De vert foncé à forest green
			transition_rgb(	10, 110, 10, 240, 250, 
							34, 110, 10, 240, 250,   
							temps_transition_secondes*1000, 1);
			transition_rgb(	34, 110, 10, 240, 250, 
							34, 139, 10, 240, 250,   
							temps_transition_secondes*1000, 1);
			transition_rgb(	34, 139, 10, 240, 250, 
							34, 139, 34, 240, 250,   
							temps_transition_secondes*1000, 1);
		}
	}

	/**
	 * Mettre une ambiance de couleur : tons rouge-rose pour la vitalité et la passion.
	 * @param temps_transition_secondes (temps conseillé, >100) sinon la transition se fait assez mal
	 * @param temps_total_secondes 	Duree totale de ce mode.
	 */
	public void set_ambiance_love( int temps_transition_secondes, int temps_total_secondes ) throws Exception {
		long temps_depart_ms = System.currentTimeMillis();
		while (!finish  && temps_total_secondes > (System.currentTimeMillis()-temps_depart_ms)*1000) {
			// Rouge a Rose
			transition_rgb(	210, 10, 30, 240, 240,
							186, 10, 30, 240, 240, 
							temps_transition_secondes*1000, 1);
			transition_rgb(	186, 10, 30, 240, 240, 
							186, 10, 211, 240, 240, 
							temps_transition_secondes*1000, 1);
			transition_rgb(	186, 10, 211, 240, 240, 
							186, 85, 211, 240, 240, 
							temps_transition_secondes*1000, 1);
			// Retour
			transition_rgb(	186, 85, 211, 240, 240, 
							186, 10, 211, 240, 240,
							temps_transition_secondes*1000, 1);
			transition_rgb(	186, 10, 211, 240, 240, 
							210, 10, 211, 240, 240,
							temps_transition_secondes*1000, 1);
			transition_rgb(	210, 10, 211, 240, 240, 
							210, 10, 30, 240, 240,
							temps_transition_secondes*1000, 1);
		}
	}

	
	
//========================================================================================	
//==================== UPDATE des parametres & Connexion =================================

	
	public void setColorType(String type)
	{
		this.colorType = type;
	}
	
	/**
	 * Permet de se connecter au Hue Bridge, et d'obtenir son @ip, et notre n° user
	 */
	public void se_connecter() {
		String rep;
		int index;
		String msg_connexion = "{\"devicetype\":\"my_hue_app#Mon_PC User_1\"}";
		boolean identification_ok = false;
		JSONObject mon_json = new JSONObject();
		
		// 1 - update de l'@IP du bridge
		add_IP = getIP_pont_proj_tut();
		url_racine = "http://"+add_IP+"/api/";
		//System.out.println("add IP : "+add_IP);
		
		// 2 - Envoyer un message de connexion
		while (!finish && !identification_ok) {
			rep = do_req_http(url_racine, msg_connexion, post);
			try {
				mon_json = new JSONObject(rep);
				// Regarder si il y a une erreur : balise error
				try {
					mon_json.getString("error");
					System.out.println("Probleme de connexion : Appuyez sur le bouton central du bridge une seconde\n");
					// Sinon attendre un peu avant de reverifier que l'user ait appuye sur le bouton et reessayer
					try {
						Thread.sleep(1000); // 1000 ms
					} catch (InterruptedException int_e) {
						System.out.println("se connecter - Echec du sleep");
					}
				} catch (JSONException e) {
					identification_ok = true;
				}
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		} // fin du while
		
		// 3 - extraction du n°User
		try {
			rep = mon_json.getJSONObject("success").getString("username");
			mon_ID_user = rep;
			url_user = url_racine+rep+"/";
			System.out.println("url user => "+url_user);
		} catch (JSONException e) {
			System.out.println("se connecter - Echec du getString username");
			e.printStackTrace();
		}
	}

	public void test_connexion() {
		if (url_user != null) {
			String rep = do_req_http(url_user, "", get);
			try {
				JSONObject mon_json = new JSONObject(rep);
				// Regarder si il y a une erreur : balise error
				try {
					mon_json.getString("error");
					System.out.println("Probleme de connexion : Tentative de redemarrage");
					se_connecter();
				} catch (JSONException e) { }
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		} else {
			se_connecter();
		}
	}
	
	/*public static void main (String[] args){
	System.out.println("Hello World");
	PhillipsLamp mon_prog = new PhillipsLamp();
	mon_prog.se_connecter();
	mon_prog.test_connexion();
	//mon_prog.clignoter_rouge_auto(10);
	//mon_prog.set_ambiance_reveil_matin(30);
	//mon_prog.set_ambiance_optimism(100, 60000);
	//mon_prog.set_ambiance_nature(100, 10000);
	mon_prog.set_ambiance_love(100, 10000);
	}*/
	
//============================ Commandes specifiques ==============================
	
	/**
	 * Allumer ou eteindre une lampe.
	 * @param est_allumee	True si la lampe est allumee, false sinon.
	 */
	public void set_etat_lampe(boolean est_allumee, int num_lampe) {
		String url_dest;
		String msg;
		// On test la connexion
		test_connexion();
		
		if (est_allumee) {
			msg = "{\"on\":true}";
		} else {
			msg = "{\"on\":false}";
		}
		
		if (num_lampe > 0) {
			url_dest = url_user+"lights/"+num_lampe+"/state";
			do_req_http(url_dest, msg, put);
		} else {
			System.out.println("set_etat_lampe : veuillez rentrer un numero de lampe entre 1 et x");
		}
	}
	
	/**
	 * Permet de changer la couleur des lampes, 
	 * on ne peut choisir que deux parametres parmi (r,g,b),
	 * les couleurs de HUE Philips sont faites comme ça :
	 * ! DONC METTRE UN PARAMETRE A -1 && AUCUN parametre ne doit être à 0 !
	 * La couleur sera le rapport entre les deux parametres choisis
	 * Ex : r=100,g=20,b=-1 si je veux une couleur rouge teinté d'un peu de vert.
	 * @param r   Rouge 0 à 254.
	 * @param g   Verte 0 à 254.
	 * @param b   Bleue 0 à 254.
	 * @param brightness	Puissance de 1 à 254.
	 * @param saturation	Saturation de 0 (blanc) à 254 (très coloré). 
	 * @param n_lampe	Le numéro de la lampe à allumer.
	 */
	public void changer_couleur(int red, int green, int blue, int brightness, int saturation, int n_lampe) {
		// On test la connexion
		test_connexion();
		// Couleur en XY sur le triangle gamut
		float X = red * 0.664511f + green * 0.154324f + blue * 0.162028f;
		float Y = red * 0.283881f + green * 0.668433f + blue * 0.047685f;
		float Z = red * 0.000088f + green * 0.072310f + blue * 0.986039f;
		if (red==0 && green==0 && blue==0) {
			System.out.println("changer_couleur - PBM red,green,blue à 0");
		} else {
			float x = X / (X + Y + Z);
			float y = Y / (X + Y + Z);
			String url_dest = url_user+"lights/"+n_lampe+"/state";
			
			int bri = brightness, sat = saturation;
			// Valeur brightness
			if (brightness < 1) {
				bri = 1;
			} else if (brightness > 254) {
				bri = 254;
			}
			// Valeur saturation
			if (sat < 0) {
				sat = 0;
			} else if (sat > 254) {
				sat = 254;
			}
			
			// 2 - Envoi de la requete http
			String req = "{\"xy\": ["+x+","+y+"], \"bri\":"+bri+", \"sat\":"+sat+" }";
			//System.out.println("changer_couleur - Requete : "+req);
			do_req_http(url_dest, req, put);
		}
	}
	
//=================================================================================
//=============================== *** Fonctionnalités *** =========================

	/**
	 * Faire clignoter la lampe en rgb.
	 * @param r	Red.
	 * @param g	Green.
	 * @param b	Blue.
	 * @param periode_ms
	 * @param temps_secondes
	 * @param n_lampe	Le numero de la lampe.
	 */
	public void clignoter(int r, int g, int b, int periode_ms, int temps_secondes, int n_lampe) {
		long start_time = System.currentTimeMillis();
		long temps_total = 0;
		
		while (!finish &&  temps_total < temps_secondes ) {
			changer_couleur(r, g, b, 250, 200, n_lampe);
			delai(periode_ms);
			changer_couleur(r, g, b, 20, 200, n_lampe);
			delai(periode_ms);
			// MAJ du temps
			temps_total = (System.currentTimeMillis() - start_time)/1000;
			System.out.println("---- Temps total : "+temps_total);
		}
	}

	/**
	 * Faire la transition entre la couleur rgb n°1 vers rgb n°2
	 * @param r1
	 * @param g1
	 * @param b1
	 * @param bri1
	 * @param sat1
	 * @param r2
	 * @param g2
	 * @param b2
	 * @param bri2
	 * @param sat2
	 * @param n_lampe
	 */
	public void transition_rgb(	int r1, int g1, int b1, int bri1, int sat1,
								int r2, int g2, int b2, int bri2, int sat2,
								int temps_ms, int n_lampe ) {
		// Pour le raffraichissement de couleur
		int delai_raffraichissement = 400;
		// Pour le temps d'execution
		long start_time = System.currentTimeMillis();
		long temps_total = 0;
		// Pour l'ecart entre les valeurs
		float R1=r1, G1=g1, B1=b1, BRI1=bri1, SAT1=sat1, R2=r2, G2=g2, B2=b2, BRI2=bri2, SAT2=sat2;
		float ecart_r=R2-R1, ecart_g=G2-G1, ecart_b=B2-B1, ecart_bri=BRI2-BRI1, ecart_sat=SAT2-SAT1;
		// Valeurs aux
		float r=R1, g=G1, b=B1, bri=BRI1, sat=SAT1;
		// Debut
		float rapport_temps = 0;
		while (!finish &&  temps_total < temps_ms ) {
			changer_couleur((int)r, (int)g, (int)b, (int)bri, (int)sat, n_lampe);
			delai(delai_raffraichissement);
			// MAJ des couleurs
			r=R1+(ecart_r*rapport_temps); g=G1+(ecart_g*rapport_temps); b=B1+(ecart_b*rapport_temps);
			bri=BRI1+(ecart_bri*rapport_temps); sat=SAT1+(ecart_sat*rapport_temps);
			//System.out.println("r("+r+")"+"g("+g+")"+"b("+b+")"+"bri("+bri+")"+"sat("+sat+")");
			System.out.println("r("+r+") g("+g+") b("+b+")");
			// MAJ du temps
			temps_total = (System.currentTimeMillis() - start_time);
			rapport_temps = (float)((float)temps_total/(float)temps_ms);
			//System.out.println("---- Temps total : "+temps_total+" rapport_temps : "+rapport_temps);
		}
	}
	
	/**
	 * Faire la transition entre rgb1 et rgb2, mais en ne changeant qu'un parametre rgb à la fois.
	 * @param r1
	 * @param g1
	 * @param b1
	 * @param bri1
	 * @param sat1
	 * @param r2
	 * @param g2
	 * @param b2
	 * @param bri2
	 * @param sat2
	 * @param temps_ms
	 * @param n_lampe
	 */
	public void transition_rgb_progressive( int r1, int g1, int b1, int bri1, int sat1,
											int r2, int g2, int b2, int bri2, int sat2,
											int temps_ms, int n_lampe ) {
		// Pour le raffraichissement de couleur
		int delai_raffraichissement = 50;
		// Pour le temps d'execution
		long start_time = System.currentTimeMillis();
		long temps_total = 0;
		// Pour l'ecart entre les valeurs
		float R1=r1, G1=g1, B1=b1, BRI1=bri1, SAT1=sat1, R2=r2, G2=g2, B2=b2, BRI2=bri2, SAT2=sat2;
		float ecart_r=R2-R1, ecart_g=G2-G1, ecart_b=B2-B1, ecart_bri=BRI2-BRI1, ecart_sat=SAT2-SAT1;
		// Valeurs aux
		float r=R1, g=G1, b=B1, bri=BRI1, sat=SAT1;
		// Debut
		float rapport_temps_bri_sat = 0;
		float rapport_temps_rgb = 0;
		float temps_div_3 = temps_ms/3;
		while (!finish &&  temps_total < temps_ms ) {
			changer_couleur((int)r, (int)g, (int)b, (int)bri, (int)sat, n_lampe);
			delai(delai_raffraichissement);
			// MAJ des couleurs
			if (temps_total < temps_div_3) {
				// Les r
				rapport_temps_rgb = (float)((float)temps_total/(float)temps_div_3);
				r=R1+(ecart_r*rapport_temps_rgb);
			} else if (temps_total < 2*temps_div_3) {
				// Les g
				rapport_temps_rgb = (float)((float)(temps_total-temps_div_3)/(float)temps_div_3);
				g=G1+(ecart_g*rapport_temps_rgb);
			} else {
				// Les b
				rapport_temps_rgb = (float)((float)(temps_total-temps_div_3*2)/(float)temps_div_3);
				b=B1+(ecart_b*rapport_temps_rgb);
			}
			System.out.println("r("+r+") g("+g+") b("+b+")");
			bri=BRI1+(ecart_bri*rapport_temps_bri_sat); sat=SAT1+(ecart_sat*rapport_temps_bri_sat);
			//System.out.println("r("+r+")"+"g("+g+")"+"b("+b+")"+"bri("+bri+")"+"sat("+sat+")");
			// MAJ du temps
			temps_total = (System.currentTimeMillis() - start_time);
			rapport_temps_bri_sat = (float)((float)temps_total/(float)temps_ms);
			//System.out.println("---- Temps total : "+temps_total+" rapport_temps : "+rapport_temps);
		}
	}

//=================================================================================
//================================= OUTILS ========================================
//=================================================================================

	/**
	 * Obtenir une @IP à partir d'une @MAC.
	 * @param  mac L'adresse MAC destination.
	 * @return     Son Adresse IP grace a la table arp.
	 */
	public String getIP(String mac) {
		String ip = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader("/proc/net/arp"));
			String line = "";
			while(!finish && (line = br.readLine()) != null) {
				String[] tokens = line.split("\\s+");
				// The ARP table has the form:
				//   IP address        HW type    Flags     HW address           Mask   Device
				//   192.168.178.21    0x1        0x2       00:1a:2b:3c:4d:5e    *      tiwlan0
				if(tokens.length >= 4 && tokens[3].equalsIgnoreCase(mac)) {	// On s'arrete sur celui de l'@MAC
					// On prend l'@IP si elle est egale a celle donnee
					ip = tokens[0];
					break;
				}
			}
			br.close();
		} catch(Exception e) { System.out.println("Erreur dans getIP"); }
		return ip;
	}

	/**
	 * Obtenir l'@IP du pont philips avec l'@MAC : 00:17:88:17:60:f2.
	 * @return Son adresse IP.
	 */
	public String getIP_pont_proj_tut() {
		return getIP("00:17:88:17:60:f2");
	}

	/**
	 * Mettre en forme un json recu.
	 * @param txt_json
	 * @return
	 */
	private String forme_json(String txt_json) {
		boolean debut_trouve = false;
		boolean fin_trouve = false;
		int pos_curseur = 0;
		int debut = 0;
		String json_formate = null;
		while (!finish && !debut_trouve && pos_curseur<=txt_json.length()) {
			//System.out.println("d "+pos_curseur);
			if (txt_json.charAt(pos_curseur) == '{') {
				debut_trouve = true;
				debut = pos_curseur;
			} else {
				pos_curseur++;
			}
		}
		if (debut_trouve) {
			pos_curseur = txt_json.length()-2;
			while (!finish && !fin_trouve && pos_curseur>=0) {
				if (txt_json.charAt(pos_curseur) == '}') {
					fin_trouve = true;
					json_formate = txt_json.substring(debut, pos_curseur+1);
				} else {
					pos_curseur--;
				}
			}
		}
		//System.out.println(json_formate);
		return json_formate;
	}

	/**
	 * Attendre X ms.
	 * @param temps_ms
	 */
	
	static private void delai(int temps_ms) {
		try {
			Thread.sleep(temps_ms);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//=============================================================================
	//======================= REQUETES HTTP =======================================
	//=============================================================================

	/**
	 * Envoyer une requette http vers une URL.
	 * @param mon_url
	 * @param msg	Texte json.
	 * @param type	put,post,get
	 * @return
	 */
	public String do_req_http(String mon_url, String msg, int type) {
		URL url;
		String reponse=null,ligne = null;
		if (add_IP != null) {
			try {
				url = new URL(mon_url);
				HttpURLConnection conn;
				try {
					conn = (HttpURLConnection) url.openConnection();
					if (type == post || type == put) {
						conn.setDoOutput(true);
					}
					try {
						if (type == post) {
							conn.setRequestMethod("POST");
						} else if(type == get) {
							conn.setRequestMethod("GET");
						} else if (type == put) {
							conn.setRequestMethod("PUT");
						}
					} catch (ProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (type == post || type == put) {
						OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
						writer.write(msg);
						writer.flush();
					}
					//recuperation du code html
					BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					while (!finish && (ligne = reader.readLine()) != null) {
					        reponse+= ligne.trim()+"\n";
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//System.out.println("\n********** Reponse a une req http ************");
		} else {
			//System.out.println("********** add IP nulle, echec req http  ************");
		}
		//System.out.println(reponse);
		return forme_json(reponse);
	}

	@Override
	public void run() 
	{
		try
		{
			if(colorType.equals("love"))
			{
				this.set_ambiance_love(100, 600);
			}
			else if(colorType.equals("nature"))
			{
				this.set_ambiance_nature(100, 600);
			}
			else if(colorType.equals("optimism"))
			{
				this.set_ambiance_optimism(100, 600);
			}
			else if(colorType.equals("intruder"))
			{
				this.clignoter_rouge_auto(600);
			}
		}
		catch(Exception e)
		{
			
		}
		System.out.println("Thread manager lamp finished.");
	}
	
}
