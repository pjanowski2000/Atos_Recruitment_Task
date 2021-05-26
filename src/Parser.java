
import java.io.File;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Parser {
    private final String filePath;

    public Parser(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }


    public BigDecimal getRate(String currency) {
        Pattern pattern = Pattern.compile("^[A-Z]{3}$");
        Matcher matcher = pattern.matcher(currency);
        boolean matchFound = matcher.find();
        if (!matchFound) {throw new IllegalArgumentException("Wrong currency format ");}
        BigDecimal value = null;
        try {
            File inputFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("Cube") ;
            for (int temp = 2; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    if(eElement.getAttribute("currency").equals(currency)){

                        value=BigDecimal.valueOf(Double.parseDouble(eElement.getAttribute("rate")));
                        System.out.println(value);
                        return value;
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if(value==null){
            throw new IllegalArgumentException("This currency not exists in database");
        }
        return value;
    }
}