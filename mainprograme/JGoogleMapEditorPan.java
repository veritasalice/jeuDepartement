package mainprograme;


import java.util.ArrayList;

import javax.swing.JEditorPane;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
 

public class JGoogleMapEditorPan extends JEditorPane {
 
	private static final long serialVersionUID = 1L;
	private int zoomFactor = 12;
    private String ApiKey = "";
    private String roadmap = "roadmap";
    public final String viewTerrain = "terrain";
    public final String viewSatellite = "satellite";
    public final String viewHybrid = "hybrid";
    public final String viewRoadmap = "roadmap";


    
    // Constructeur
    public JGoogleMapEditorPan() {
        HTMLEditorKit kit = new HTMLEditorKit();
        HTMLDocument htmlDoc = (HTMLDocument) kit.createDefaultDocument();
        this.setEditable(false);
        this.setContentType("text/html");
        this.setEditorKit(kit);
        this.setDocument(htmlDoc);
    }
 
    /**
     * Fixer le zoom
     * @param zoom valeur de 0 à 21
     */
    public void setZoom(int zoom) {
        this.zoomFactor = zoom;
    }
 
    /**
     * Fixer la clé de developpeur
     * @param key APIKey fourni par Google
     */
    public void setApiKey(String key) {
        this.ApiKey = key;
    }
 
    /**
     * Fixer le type de vue
     * @param roadMap
     */
    public void setRoadmap(String roadMap) {
        this.roadmap = roadMap;
    }
 
    /**
     * Afficher la carte d'après des coordonnées GPS
     * @param latitude
     * @param longitude
     * @param width
     * @param height
     * @throws Exception erreur si la APIKey est non renseignée
     */
    public void showCoordinate(String latitude, String longitude, int width, int height) throws Exception {
        this.setMap(latitude, longitude, width, height);
    }
 


    /**
     * Assembler l'url et Générer le code HTML
     * @param x
     * @param y
     * @param width
     * @param height
     * @throws Exception
     */
    private void setMap(String x, String y, Integer width, Integer height) throws Exception {
        if (this.ApiKey.isEmpty()) {
            throw new Exception("Developper API Key not set !!!!");
        }
 
        String url = "https://maps.googleapis.com/maps/api/staticmap?";
        url += "center=" + x + "," + y;
        url += "&zoom=" + this.zoomFactor;
        url += "&size=" + width.toString() + "x" + height.toString();
        url += "&maptype=" + this.roadmap;
        url += "&markers=color:blue%7Clabel:S%7C" + x + "," + y;
        url += "&sensor=false";
        url += "&key=" + this.ApiKey;

        String html = "<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>";
        html += "<html><head></head><body>";
        html += "<img src='" + url + "'>";
        html += "</body></html>";
        this.setText(html);
    }
    /**
    
     * @param a
     * @param b
     * @param passant
     * @throws Exception
     */
    private void setRoute(Integer width, Integer height, ArrayList<Point> chemin,String color) throws Exception {
        if (this.ApiKey.isEmpty()) {
            throw new Exception("Developper API Key not set !!!!");
        }
        
				
        String url = "https://maps.googleapis.com/maps/api/staticmap?";
        url += "center=" + 46.66 + "," + 2.66;
        url += "&zoom=" + 6;
        url += "&size=" + width.toString() + "x" + height.toString();
        url += "&path=";
        url	+= "color:"+ color +"|weight:6";
        for(int i=0;i<chemin.size();i++){
			url += "|";
			url += chemin.get(i).getX()+","+chemin.get(i).getY();
		}
        
        //url +=  "|40.737102,-73.990318|40.749825,-73.987963|40.752946,-73.987384|40.755823,-73.986397";
        
        url += "&maptype=" + this.roadmap;
        url += "&sensor=false";
        url += "&key=" + this.ApiKey;
        
        System.out.println(url);
        
        String html = "<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>";
        html += "<html><head></head><body>";
        html += "<img src='" + url + "'>";
        html += "</body></html>";
        this.setText(html);
    }
    /**
     * Afficher la carte la route
     * @param origin
     * @param destination
     * @param passant

     * @throws Exception erreur si la APIKey est non renseignée
     */
    public void showRoute(int width,int height,ArrayList<Point> chemin,String color) throws Exception {
        this.setRoute(width,height,chemin,color);
    }
}
