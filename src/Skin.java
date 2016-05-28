import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;  
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
//import static skin.Valuetable.valt;
//import skin.Table1;
public class Skin {
DefaultTableModel dtm;
    /**
     * @param args the command line arguments
     * @throws java.ios.IOException
 **/   
    public static void main(String[] args) throws IOException {
        
      // File file1 = new File("C:\\Users\\Shweta Shimpi\\Pictures\\boat.jpg");      
//            BufferedImage image1 = ImageIO.read(file1);
//            System.out.println("Success");
             
 JFrame f = new Display();
 //dtm= (DefaultTableModel) valt.getModel();
f.setBounds(200, 200, 400, 200);
f.setVisible(true);
f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//Table1 gui=new Table1();
//gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//gui.setSize(600,50);
//gui.setVisible(true); 



f.addWindowListener(new WindowAdapter() {
@Override
public void windowClosing(WindowEvent e)
{
System.exit(0);
}
});
            
       

    }
    
}
