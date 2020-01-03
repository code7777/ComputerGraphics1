/*
      Course: CS 45500
      Name: Preston Porter
      Assignment: 1

*/

import framebuffer.FrameBuffer;
import java.io.FileInputStream;
//import java.io.File;

import java.awt.Color;

/**


*/
public class Hw_1
{
   public static void main(String[] args)
   {
      // Check for a file name on the command line.
      if ( 0 == args.length )
      {
         System.err.println("Usage: java Hw_1 <PPM-file-name>");
         System.exit(-1);
      }

      // This framebuffer holds the image that will be embedded
      // within a viewport of our larger framebuffer.
      FrameBuffer fbEmbedded = new FrameBuffer( args[0] );

      /******************************************/
      Color tan = new Color(255,189,96);
       Color mahagony = new Color(192,56,14); 
        
      // Your code goes here.
      // Create a framebuffer. Fill it with the checkerboard pattern.
      FrameBuffer initFB = new FrameBuffer(1000,600);
      
      //FrameBuffer.Viewport tanFB = initFB.new Viewport(100 +100*i ,100 +100*j , tan); 
      //FrameBuffer.Viewport mhgyFB = initFB.new Viewport(100 +100*i ,100 +100*j , mahagony);
      initFB.setBgColorFB(tan);
      
      for(int i=0;i<1000;i++) //collumns
      {
        for(int j=0;j<600;j++) //rows
        {
          if( ((i <101) && (j<101)|(((200<j)&&(j<301)) | ((400<j)&&(j<501))))|
               ((((100<i)&&(i <201)) &&((100<j)&&(j<201))|(((300<j)&&(j<401)) | ((500<j)&&(j<601)))) )|
                 ((((200<i)&&(i <301)) &&(j<101)|(((200<j)&&(j<301)) | ((400<j)&&(j<501))))|
                  ((((300<i)&&(i <401)) &&((100<j)&&(j<201))|(((300<j)&&(j<401)) | ((500<j)&&(j<601)))) )|
                   ((((400<i)&&(i <501)) &&(j<101)|(((200<j)&&(j<301)) | ((400<j)&&(j<501))))|
                    ((((500<i)&&(i <601)) &&((100<j)&&(j<201))|(((300<j)&&(j<401)) | ((500<j)&&(j<601)))) )|
                     ((((600<i)&&(i <701)) &&(j<101)|(((200<j)&&(j<301)) | ((400<j)&&(j<501))))|
                      ((((700<i)&&(i <801)) &&((100<j)&&(j<201))|(((300<j)&&(j<401)) | ((500<j)&&(j<601)))) )|
                       ((((800<i)&&(i <901)) &&(j<101)|(((200<j)&&(j<301)) | ((400<j)&&(j<501)))))|
                        ((((900<i)&&(i <1001)) &&((100<j)&&(j<201))|(((300<j)&&(j<401)) | ((500<j)&&(j<601))))
        ))))){
          initFB.setPixelFB(i,j,tan);
          }
          else initFB.setPixelFB(i,j,mahagony);
        }
      }
   
      // Create a viewport to hold the given PPM file. Put the PPM file in the viewport.
       FrameBuffer.Viewport newVP = initFB.new Viewport( 75, 125, fbEmbedded);
       
      // Create another viewport and fill it with a flipped copy of the PPM file.
       mirrorFB(fbEmbedded);
       FrameBuffer.Viewport newVP2 = initFB.new Viewport( 330, 125, fbEmbedded);
      // Create another viewport and fill it with the striped pattern.
       
       Color pink = new Color(241, 95, 116);
       Color green = new Color(152, 203, 74);
       Color blue = new Color(84, 129, 230);
       Color[] stripes = {pink, green ,blue };
      
      for(int x = 0 ;x < 300; x++) //collumns
      {
        for(int y = 0; y < 120; y++) //rows
        {
          initFB.setPixelFB(610 + x, 420 + y, stripes[((x + y) / 30) % 3]);
        }
      }
      // Create another viewport that covers the selected region of the framebuffer.
      
      FrameBuffer.Viewport newVP3 = initFB.new Viewport(500, 200, 200, 300);
      // Create another viewport to hold a copy of the selected region.
       FrameBuffer  newFB3 = newVP3.convertVP2FB();
       FrameBuffer.Viewport newVP4 = initFB.new Viewport(725, 25, 250, 350);
     
      // Give this viewport a grayish background color.
        newVP4.clearVP(new Color(192, 192, 192));
      // Create another viewport inside the last one.
      // Copy the selected region's viewport into this last viewport.
       FrameBuffer.Viewport newVP5 = initFB.new Viewport(750, 50, newFB3);
      //FrameBuffer fb = null;


      /******************************************/
      // Save the resulting image in a file.
      String savedFileName = "Hw_1.ppm";
      initFB.dumpFB2File( savedFileName );
      System.err.println("Saved " + savedFileName);
   }
   
   // Creates mirror of RebelTrooper image
   
   public static void mirrorFB(FrameBuffer reflection)
   {
     int middle;//middle of picture
     
     if((reflection.getWidthFB() % 2) == 0)
     {
       middle = (reflection.getWidthFB() / 2); //even
     }   
     else
     {
       middle = ((reflection.getWidthFB()-1) / 2); //odd
     }
     
     for(int i = 0; i < middle; i++) //rows
     {
       int rightSide = middle + i;
       int leftSide = middle - i;
       
       for(int y=0; y < reflection.getHeightFB(); y++) //collumns
       {
         Color c1 = reflection.getPixelFB(rightSide, y);
         Color c2 = reflection.getPixelFB(leftSide, y);
         reflection.setPixelFB(rightSide, y, c2);
         reflection.setPixelFB(leftSide, y, c1);
       }
     }
   }
}
