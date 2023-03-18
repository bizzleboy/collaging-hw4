
The purpose of this program is to allow users to create and upload PPM images, edit them by overlaying more images, add filters to specific layers, and save the resulting images and files for future use. The following interfaces are used in this project:




Interfaces

CollageController:
This interface is used to make the controller for the program. The controller handles inputs from the user in the form of text-based commands about the project, and then sends those commands to the model for editing the contents of the project.
This interface has only one method, and it is used for running the program.

Project:
This interface is used to make the model for the program. It will hold all of the information about a specific project, including any images and layers for that project.




Classes (Controller Package)

Main:
This class runs the program.
The main() method instantiates a CollageControllerImpl object and uses it to run the program.

CollageControllerImpl:
This class implements the CollageController interface. It is found in the Controller package.
The runProgram() method waits for users, which will be defined in the Scripts of Commands section, and connects with the model accordingly.


Classes (Model Package)

BasicCollageProject:
This class represents a project for editing. It holds any number of layers, and has a set width, height, and max pixel value.
This class is initially instantiated with one layer of the specified width and height full of transparent white pixels.
This class has methods for adding more layers, adding images to layers, setting the filters of layers, saving the project’s contents to a file, and saving the project’s resulting image as a PPM file.

Layer:
This class represents a layer that exists in a project and holds a list of images. It also has a specific filter, a name, and a width and height.
This class has methods for placing an image on the background image of the layer by editing the pixel values on the background image, and then storing that image in the list of images.
This class also has a method for applying a filter to the layer and all of the pixel’s in its base image.
This class also has methods for saving the contents of the layer’s images into a string format that is readable by a PPM or by the TXT project file type.
This class also has methods for getting it’s fields.

Image:
This class represents a specific grid of pixels that comprise an image. It holds a grid of pixels of any size, although only those pixels within the bounds of the project will be seen if the image is saved.
This class has methods for changing it’s pixel’s filtered colors based on red, blue and green filtering, along with brightening or darkening by values, intensities or lumas.

Pixel:
This class represents a single pixel in an image. It has a red value, a green value, a blue value, an alpha intensity, and a resultant 3-channel RBG color from all of those values. The red, green, blue and alpha values must all be between 0 and 255.
This class has methods for filtering the pixels to only show red values or green values or blue values.
It also has methods for darkening or brightening the pixel’s reds, greens and blues by a specified value, intensity of luma.
It also has methods for adding the values of one pixel to the top of the other, with specific math for handling opacity from alpha values.




Script of Commands

Create a new 100 pixel by 100 pixel project with the following command line prompt:

`new-project 100 100
`

Add an additional layer labelled “layer-2” with the following command line prompt:

`add-layer layer-2`


Add an image with the specified filepath to the first layer of the project, offset by 5 pixels right and 5 pixels down, with the following command line prompt:

`add-image layer-1 Collaging/src/newSprite.ppm 5 5`


Filter out all colors except green on layer-1 with the following command line prompt:

`set-filter layer-1 green-component`


Save the image of the project at a specific file location with the following command line prompt:

`save-image Collaging/src/wawa10.ppm`


Save the project at a specific file location with the following command line prompt:

`save-project Collaging/src/project.txt`