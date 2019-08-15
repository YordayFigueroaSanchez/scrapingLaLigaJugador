package scraping;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Attr;

public class ScrapingLaLigaJugador {

	public static final String xmlFilePath = "xmlfile.xml";

	public static void main(String[] args) throws ParserConfigurationException, TransformerConfigurationException {
		// TODO Auto-generated method stub

		String url = "https://coinmarketcap.com/";
		String url2 = "http://puntos.laligafantasymarca.com/jugadores/dani-parejo";
		
		String file = "ejemplo.html";

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// elemento raiz
		org.w3c.dom.Document doc = docBuilder.newDocument();
		org.w3c.dom.Element rootElement = doc.createElement("jornadas");
		doc.appendChild(rootElement);

//		if (getStatusConnectionCode(url2) == 200) {
		if (getStatusFile(file) == 1) {
//			Document documento = getHtmlDocument(url2);
			Document documento = getHtmlFileToDocument(file);
			
			Elements elemntosPrueba = documento.select("h1.name");
			Elements elemntosDataJugador = documento.select("tbody").get(0).select("tr");
			
//			Elements elementos = documento.select("#currencies tr:has(td.text-center)");
			System.out.println(elemntosDataJugador.size());
			for (Element elem : elemntosDataJugador) {
//				String titulo = elem.getElementsByClass("text-center").text();
//				String autor = elem.getElementsByClass("currency-name-container").text();
//				String marketCap = elem.getElementsByClass("market-cap").text();
//				String price = elem.getElementsByClass("price").text();
				Elements elementosTD = elem.select("td");
				
				// JORNADA DATA
				org.w3c.dom.Element jornada = doc.createElement("jornada");
				rootElement.appendChild(jornada);
				
				
//				Jor: Jornada
				String numeroJornada = elementosTD.get(0).text();
				Attr attrJornada = doc.createAttribute("jornadaNumero");
				attrJornada.setValue(numeroJornada);
				jornada.setAttributeNode(attrJornada);
				
//				Rival: Equipo rival
				String rival = elementosTD.get(1).text();
				Attr attrRival = doc.createAttribute("rival");
				attrRival.setValue(rival);
				jornada.setAttributeNode(attrRival);
				
//				Pj: Partido jugado
				String partidoJugado = elementosTD.get(2).text();
				Attr attrpartidoJugado = doc.createAttribute("partidoJugado");
				attrpartidoJugado.setValue(partidoJugado);
				jornada.setAttributeNode(attrpartidoJugado);
				
//				+60': 60' jugados o más (masde60)
				String masDe60Min = elementosTD.get(3).text();
				Attr attrmasDe60Min = doc.createAttribute("masDe60Min");
				attrmasDe60Min.setValue(masDe60Min);
				jornada.setAttributeNode(attrmasDe60Min);
				
//				G: Goles
				String goles = elementosTD.get(4).text();
				Attr attrgoles = doc.createAttribute("goles");
				attrgoles.setValue(goles);
				jornada.setAttributeNode(attrgoles);
				
//				Ag: Asistencias de gol
				String asistenciaDeGol = elementosTD.get(5).text();
				Attr attrasistenciaDeGol = doc.createAttribute("asistenciaDeGol");
				attrasistenciaDeGol.setValue(asistenciaDeGol);
				jornada.setAttributeNode(attrasistenciaDeGol);
				
//				Asg: Asistencias sin gol
				String asistenciaSinGol = elementosTD.get(6).text();
				Attr attrasistenciaSinGol = doc.createAttribute("asistenciaSinGol");
				attrasistenciaSinGol.setValue(asistenciaSinGol);
				jornada.setAttributeNode(attrasistenciaSinGol);
				
//				Lle: LLegadas al área
				String llegadasAlArea = elementosTD.get(7).text();
				Attr attrllegadasAlArea = doc.createAttribute("llegadasAlArea");
				attrllegadasAlArea.setValue(llegadasAlArea);
				jornada.setAttributeNode(attrllegadasAlArea);
				
//				Pty p: Penaltis provocados
				String penaltisProvocados = elementosTD.get(8).text();
				Attr attrpenaltisProvocados = doc.createAttribute("penaltisProvocados");
				attrpenaltisProvocados.setValue(penaltisProvocados);
				jornada.setAttributeNode(attrpenaltisProvocados);
				
//				P 0: Portería a cero
				String porteriaEnCero = elementosTD.get(9).text();
				Attr attrporteriaEnCero = doc.createAttribute("porteriaEnCero");
				attrporteriaEnCero.setValue(porteriaEnCero);
				jornada.setAttributeNode(attrporteriaEnCero);
				
//				Pty d: Penaltis parados
				String penaltisParados = elementosTD.get(10).text();
				Attr attrpenaltisParados = doc.createAttribute("penaltisParados");
				attrpenaltisParados.setValue(penaltisParados);
				jornada.setAttributeNode(attrpenaltisParados);
				
//				P: Paradas
				String paradas = elementosTD.get(11).text();
				Attr attrparadas = doc.createAttribute("paradas");
				attrparadas.setValue(paradas);
				jornada.setAttributeNode(attrparadas);
				
//				D: Despejes
				String despejes = elementosTD.get(12).text();
				Attr attrdespejes = doc.createAttribute("despejes");
				attrdespejes.setValue(despejes);
				jornada.setAttributeNode(attrdespejes);
//				Pty f: Penaltis fallados
				String penaltisFallados = elementosTD.get(13).text();
				Attr attrpenaltisFallados = doc.createAttribute("penaltisFallados");
				attrpenaltisFallados.setValue(penaltisFallados);
				jornada.setAttributeNode(attrpenaltisFallados);
//				Gpp: Goles en propia meta
				String golesEnPropiaMeta = elementosTD.get(14).text();
				Attr attrgolesEnPropiaMeta = doc.createAttribute("golesEnPropiaMeta");
				attrgolesEnPropiaMeta.setValue(golesEnPropiaMeta);
				jornada.setAttributeNode(attrgolesEnPropiaMeta);
//				Ge: Goles encajados
				String golesEncajados = elementosTD.get(15).text();
				Attr attrgolesEncajados = doc.createAttribute("golesEncajados");
				attrgolesEncajados.setValue(golesEncajados);
				jornada.setAttributeNode(attrgolesEncajados);
//				Ta: Tarjetas amarillas
				String tarjetasAmarillas = elementosTD.get(16).text();
				Attr attrtarjetasAmarillas = doc.createAttribute("tarjetasAmarillas");
				attrtarjetasAmarillas.setValue(tarjetasAmarillas);
				jornada.setAttributeNode(attrtarjetasAmarillas);
//				Tr: Tarjetas rojas
				String tarjetasRojas = elementosTD.get(17).text();
				Attr attrtarjetasRojas = doc.createAttribute("tarjetasRojas");
				attrtarjetasRojas.setValue(tarjetasRojas);
				jornada.setAttributeNode(attrtarjetasRojas);
//				Rm: Remates
				String remates = elementosTD.get(18).text();
				Attr attrremates = doc.createAttribute("remates");
				attrremates.setValue(remates);
				jornada.setAttributeNode(attrremates);
//				Rg: Regates
				String regates = elementosTD.get(19).text();
				Attr attrregates = doc.createAttribute("regates");
				attrregates.setValue(regates);
				jornada.setAttributeNode(attrregates);
//				Rec: Recuperaciones
				String recuperaciones = elementosTD.get(20).text();
				Attr attrrecuperaciones = doc.createAttribute("recuperaciones");
				attrrecuperaciones.setValue(recuperaciones);
				jornada.setAttributeNode(attrrecuperaciones);
//				Per: Pérdidas
				String perdidas = elementosTD.get(21).text();
				Attr attrperdidas = doc.createAttribute("perdidas");
				attrperdidas.setValue(perdidas);
				jornada.setAttributeNode(attrperdidas);
//				Tot: Total
				String puntoTotal = elementosTD.get(23).text();
				Attr attrpuntoTotal = doc.createAttribute("puntoTotal");
				attrpuntoTotal.setValue(puntoTotal);
				jornada.setAttributeNode(attrpuntoTotal);
//				MAR: Puntos MARCA
				String puntoMarca = elementosTD.get(22).text();
				Attr attrpuntoMarca = doc.createAttribute("puntoMarca");
				attrpuntoMarca.setValue(puntoMarca);
				jornada.setAttributeNode(attrpuntoMarca);

			}
		}

		
		//nombre del fichero
		Date  fecha = new Date();
		DateFormat hourdateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss"); 
		System.out.println("Hora y fecha: "+hourdateFormat.format(fecha));
		String nombreFichero = hourdateFormat.format(fecha);
		
		// escribimos el contenido en un archivo .xml
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		String ruta = "dataXML\\";
		StreamResult result = new StreamResult(new File(ruta,nombreFichero + ".xml"));

		// StreamResult result = new StreamResult(new File("archivo.xml"));
		// Si se quiere mostrar por la consola...
		// StreamResult result = new StreamResult(System.out);
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("File saved!");

	}

	/**
	 * Con esta método compruebo el Status code de la respuesta que recibo al hacer
	 * la petición EJM: 200 OK 300 Multiple Choices 301 Moved Permanently 305 Use
	 * Proxy 400 Bad Request 403 Forbidden 404 Not Found 500 Internal Server Error
	 * 502 Bad Gateway 503 Service Unavailable
	 * 
	 * @param url
	 * @return Status Code
	 */
	public static int getStatusConnectionCode(String url) {

		Response response = null;

		try {
			response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).ignoreHttpErrors(true).execute();
		} catch (IOException ex) {
			System.out.println("Excepción al obtener el Status Code: " + ex.getMessage());
		}
		return response.statusCode();
	}

	/**
	 * Con este método devuelvo un objeto de la clase Document con el contenido del
	 * HTML de la web que me permitirá parsearlo con los métodos de la librelia
	 * JSoup
	 * 
	 * @param url
	 * @return Documento con el HTML
	 */
	public static Document getHtmlDocument(String url) {

		Document doc = null;
		try {
			doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();
		} catch (IOException ex) {
			System.out.println("Excepción al obtener el HTML de la página" + ex.getMessage());
		}
		return doc;
	}
	
	public static int getStatusFile(String file) {
		return 1;
	}
	
	public static Document getHtmlFileToDocument(String file) {

		File input = new File("data/"+file);
		Document doc = null;
		try {
			doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
		} catch (IOException ex) {
			System.out.println("Excepción al obtener el HTML de la página" + ex.getMessage());
		}
		return doc;
	}
}
