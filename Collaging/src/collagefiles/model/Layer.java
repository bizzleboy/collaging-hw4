package collagefiles.model;

import java.util.ArrayList;
import java.util.List;

public class Layer {
  List<Image> imagesOnLayer;
  private String filter;
  private String name;
  private int width;
  private int height;

  public Layer(String name,int width,int height){
    this.imagesOnLayer = new ArrayList<Image>();
    this.name = name;
    this.filter = "normal";
    this.width =width;
    this.height =height;
    Image background = new Image(width, height);
    this.imagesOnLayer.add(background);
  }

  public void placeImage(int xPos, int yPos,Image image) throws IllegalArgumentException{
    if (xPos <0 || yPos >= this.imagesOnLayer.get(0).pixels.size() ||yPos <0 || xPos >= this.imagesOnLayer.get(0).pixels.get(0).size() ){
      throw new IllegalArgumentException("Invalid positions");
    }
    int xIndex = xPos;
    int yIndex = yPos;


    for (List<Pixel> list:image.pixels) {
      if (yIndex >= this.imagesOnLayer.get(0).pixels.size()) {
        break;
      } else {
        for (Pixel p : list) {
          if (xIndex >= this.imagesOnLayer.get(0).pixels.get(0).size()) {
            break;
          } else {
            Pixel alteredPixel = this.imagesOnLayer.get(0).pixels.get(yIndex).get(xIndex);

            alteredPixel.addPixels(p);

//          this.imagesOnLayer.get(0).pixels.get(yIndex).set(xIndex,
            this.imagesOnLayer.get(0).pixels.get(yIndex).set(xIndex, alteredPixel);
            xIndex += 1;

          }
        }
        xIndex = xPos;
        yIndex += 1;
      }
    }
    this.imagesOnLayer.add(image);
    }





  public void applyFilter(String filter){
   switch(filter){
     case "normal":
       this.filter = filter;
       for(Image image:imagesOnLayer) {
         image.filterPixels = image.pixels;
         break;
       }
     case"red-component":
       this.filter = filter;
       for(Image image:imagesOnLayer){
         image.filterImageRed();
         break;
         }
     case"blue-component":
       this.filter = filter;
       for(Image image:imagesOnLayer){
         image.filterImageBlue();
         break;
       }
     case"green-component":
       this.filter = filter;
       for(Image image:imagesOnLayer){
         image.filterImageGreen();
         break;
       }
     case"brighten-value":
       this.filter = filter;
       for(Image image:imagesOnLayer){
         image.brightenImage("brighten-value");
         break;
       }
     case"darken-value":
       this.filter = filter;
       for(Image image:imagesOnLayer){
         image.darkenImage("darken-value");
         break;
       }
     case"brighten-luma":
       this.filter = filter;
       for(Image image:imagesOnLayer){
         image.brightenImage("brighten-luma");
         break;
       }
     case"darken-luma":
       this.filter = filter;
       for(Image image:imagesOnLayer){
         image.darkenImage("darken-luma");
         break;
       }
     case"brighten-intensity":
       this.filter = filter;
       for(Image image:imagesOnLayer){
         image.brightenImage("brighten-intensity");
         break;
       }
     case"darken-intensity":
       this.filter = filter;
       for(Image image:imagesOnLayer){
         image.darkenImage("darken-intensity");
         break;
       }
     default: System.out.println("Invalid Input!");
       }

   }

   public List<Image> getImages() {
    return this.imagesOnLayer;
   }

   public String getImagePPM() {
    String imageString = "";
       for (List<Pixel> list:this.imagesOnLayer.get(0).pixels){
         for(Pixel p: list) {
           imageString += (String.format("%d %d %d\n",
                   p.getPixelColor().getRed(),
                   p.getPixelColor().getGreen(),
                   p.getPixelColor().getGreen()
                   ));
           }
         }
       return imageString;
  }

  public String getImageTxt() {
    String imageString = "";
    for (List<Pixel> list:this.imagesOnLayer.get(0).pixels){
      for(Pixel p: list) {
        imageString += (String.format("%d %d %d %d\n",
                p.getPixelColor().getRed(),
                p.getPixelColor().getGreen(),
                p.getPixelColor().getGreen(),
                p.getPixelColor().getAlpha()
        ));
      }
    }
    return imageString;
  }

  public String getFilter() {
    return this.filter;
  }

  public String getName() {
    return this.name;
  }
}




