/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
//import static skin.Valuetable.valt;

/**
 *
 * @author ManojK
 */
public class Display extends JFrame implements ActionListener
{
// Valuetable v=new Valuetable();
    //Skin s=new Skin();
JFileChooser chooser = new JFileChooser();
JButton jbutton = new JButton("Display file chooser");
JLabel picArea= new JLabel();
int c=0;   
int a=0;
    //Object data[][]; 
 
int rMax=0,bMax=0,gMax=0;
int rMin=255,gMin=255,bMin=255;
public Display()
{
super();
Container contentPane = getContentPane();
contentPane.setLayout(new FlowLayout());
contentPane.add(jbutton);
jbutton.addActionListener(this);
contentPane.add(picArea);
}
@Override
public void actionPerformed(ActionEvent e)
{
    int result = chooser.showOpenDialog(null);
    File fileobj = chooser.getSelectedFile();
    
    
    if(result == JFileChooser.APPROVE_OPTION) {
    //picArea.setText("You chose " + fileobj.getPath());
       // System.out.println("here1");
        String path= fileobj.getPath();
        ImageIcon pic = new ImageIcon(path);
        picArea.setIcon(pic);
        try {
        BufferedImage image1;
        image1 = ImageIO.read(fileobj);
        marchThroughImage(image1);
    } catch (IOException ex) {
       
    }
        
        
    } else if(result == JFileChooser.CANCEL_OPTION) {

    }
      

}
@SuppressWarnings("empty-statement")
 public boolean printPixelARGB(int pixel,int row,int i,int j) {
    int alpha = (pixel >> 24) & 0xff;
    int red = (pixel >> 16) & 0xff;
    int green = (pixel >> 8) & 0xff;
    int blue = (pixel) & 0x00ff;
    System.out.print("Alpha:" + alpha + "|  Red:" + red + "|  Green:" + green + "|  Blue:" + blue + "|  ");
    
    int  y  = (int)( 0.299   * red + 0.587   * green + 0.114   * blue);
    int cb = (int)(-0.16874 * red - 0.33126 * green + 0.50000 * blue);
    int cr = (int)( 0.50000 * red - 0.41869 * green - 0.08131 * blue); 
    System.out.print("y:" +y+ "|  cb:" +cb+ "|  cr:" +cr+"|  ");
    
    float[] hsb = Color.RGBtoHSB(red, green, blue, null);
    System.out.print("H:"+ hsb[0]+"|  S:"+hsb[1]+"|  V:"+hsb[2]);
    //pixch(red,blue,green,pic);
    int a = Math.max(red, Math.max(green, blue)) - Math.min(red, Math.min(green, blue));
   if(((hsb[0] >= 0.0) && (hsb[0] <= 50.0) && (hsb[1] >= 0.23) && (hsb[1] <= 0.68) && red>95 && green>40 && blue>20 && red>green && red>blue &&  Math.abs(red-green)>15 && a>15) || ( red>95 && green>40 && blue>20 && red>green && red>blue &&  Math.abs(red-green)>15 && a>15 && cr<=1.5862 *cb + 20 && cr>= 0.3448 *cb + 76.2069 &&cr >= -4.5652 *cb + 234.5652 &&cr <= -1.15 *cb + 301.75 &&cr<= -2.2857 *cb + 432.85 && 135<cr && cr<180 && 85<cb&& cb<135  && y>80 ))
 
    {//c++;
                System.out.println("It is a skin pixel");
                
                if(red>rMax)
                    rMax=red;
                if(green>gMax)
                    gMax=green;
                if(blue>bMax)
                    bMax=blue;
                
                if(red<rMin)
                    rMin=red;
                if(green<gMin)
                    gMin=green;
                if(blue<bMin)
                    bMin=blue;
              a=1; 
              
            
                int k=0;
                /*data[row][k]=new Integer(i);
                k++;
                 data[row][k]=new Integer (j);
                k++;
                 data[row][k]=new Integer (red);
                k++;
                 data[row][k]= new Integer (green);
                k++;
                 data[row][k]=new Integer (blue);
                k++;
                 data[row][k]=new Float (hsb[0]);
                k++;
                data[row][k]=new Float (hsb[1]);
                k++;
                 data[row][k]=new Float (hsb[2]);
                k++;
                 data[row][k]=new Float (y);
                k++;
                 data[row][k]=new Float (cb);
                k++;
                 data[row][k]=new Float (cr);
                k++;
                 data[row][k]=new Float (a);
                k++;
                 
                
                row++;
                */
               
                
               //s.dtm.addRow(new Object[]{1,1,1,1,1,1,1,1,1,1,1,1});                
            //    System.out.println(i, j, red, green,blue,y,cb,cr ,hsb[0], hsb[1], hsb[2]);
            return true;
            }
   else
        {
		System.out.print("    Not a skin pixel");
		a=0;
                return false;
		}

 }
 /*public void pixch(int red,int green,int blue, BufferedImage img)
 {
     int col = (red << 16) | (green << 8) | blue;
     img.setRGB( col);
 }*/
  private void marchThroughImage(BufferedImage pic) {
    int w = pic.getWidth();
    int h = pic.getHeight();
    int row=0;
    System.out.println("width, height: " + w + ", " + h);
 //data=new Object[w*h][12];
    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        System.out.print("x:" + j + "|  y:" + i+"|  ");
        int pixel = pic.getRGB(j, i);
      //  System.out.print(pixel);
        if(!printPixelARGB(pixel,row,i,j))
		pic.setRGB(j,i, 0);

      //    System.out.println("Red " + rMin + " " + rMax);
        //  System.out.println("Blue " + bMin + " " + bMax);
          //System.out.println("Green " + gMin + " " + gMax);
//ycbcr[0] = y;
//ycbcr[1] = cb;
//ycbcr[2] = cr;         

        System.out.println("");
      }
    }
  JLabel picLabel = new JLabel(new ImageIcon(pic));
add(picLabel);
    

    /**
     * Creates new form Display
     */
 
    
  }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
                        

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify                     
    // End of variables declaration                   


    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        picc = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        picc.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(picc)
                .addContainerGap(219, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(226, Short.MAX_VALUE)
                .addComponent(picc)
                .addGap(60, 60, 60))
        );

        pack();
    }// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify                     
    private javax.swing.JLabel picc;
    // End of variables declaration                   

}
