The purpose of this program is to allow users to create and upload PPM images, edit them by
overlaying more images, add filters to specific layers, and save the resulting images and files for
future use. The following interfaces are used in this project:

New Additions for A6:


A saveImageNew method was added to the CollageControllerImpl class. This method takes in a file path and a file name, and uses that information to save an image in either PPM, PNG or JPEG format. If the format is PPM, the buffered image of the file and the file itself are sent to the saveAsPPM method.

A saveImagePPM method was added to the CollageControllerImpl class. This method takes in a BufferedImage and a File, and converts the file to PPM format.

A printPath method was added to the CollageControllerImpl class. This method returns a string of the absolute file path. This method is used to adjust relative script paths into the absolute file path of the computer utilizing this software. This absolute file paths are necessary for loading, editing, and saving images and projects.

The saveImage method was removed from the Project interface and the BasicCollageProject class. This is because the saveImage functionality should be in the controller since it deals with editing files inputted by the user. The model should only deal with the functionality of the software independent of how it's controlled by user input.

The view was also decoupled by removing any instance of the Controller classes or any classes from the Model classes (i.e. Pixel class and Image class)


Originals from A5:

Interfaces

CollageView:
This interface is used to make a Collaging view mock. It is used for testing purposes, and has a
method for rendering messages.

GUIView:
This interface is used to make a Collaging GUI view. It has methods for setting action listeners on
the view screen, getting the frame of the view, rendering a message on the view, rendering inputs on
the view, and removing layers form the view's project.

CollageController:
This interface is used to make the controller for the program. The controller handles inputs from
the user in the form of text-based commands about the project, and then sends those commands to the
model for editing the contents of the project.
This interface has only one method, and it is used for running the program.

Project:
This interface is used to make the model for the program. It will hold all of the information about
a specific project, including any images and layers for that project.

Classes (Controller Package)

Main:
This class runs the program.
The main() method instantiates a CollageControllerImpl or a GUIController2 depending on the command
line command. If a CollageControllerImp is loaded, then an additional command will automatically
reads in a text file of scripts to be completed. Otherwise, the view is open to user input.

CollageControllerImpl:
This class implements the CollageController interface. It is found in the Controller package.
The runProgram() method waits for users, which will be defined in the Scripts of Commands section,
and connects with the model accordingly.

GUIController2
This class is a Controller for the GUI version of Collaging. It holds a view object, a model object,
and x and y offsets as fields. It implements the methods of the java class ActionListener while
extending Frame. It has an additional method for checking when actions are performed using an
ActionEvent

Classes (Model Package)

BasicCollageProject:
This class represents a project for editing. It holds any number of layers, and has a set width,
height, and max pixel value.
This class is initially instantiated with one layer of the specified width and height full of
transparent white pixels.
This class has methods for adding more layers, adding images to layers, setting the filters of
layers, saving the project’s contents to a file, and saving the project’s resulting image as a PPM
file. To assist this process, it also has a method that forms a composite image of every layer with
the filters applied.

Layer:
This class represents a layer that exists in a project and holds a list of images. It also has a
specific filter, a name, and a width and height.
This class has methods for placing an image on the background image of the layer by editing the
pixel values on the background image, and then storing that image in the list of images.
This class also has a method for applying a filter to the layer and all of the pixel’s in its base
image.
This class also has methods for saving the contents of the layer’s images into a string format that
is readable by a PPM or by the TXT project file type.
This class also has methods for getting it’s fields.

Image:
This class represents a specific grid of pixels that comprise an image. It holds a grid of pixels of
any size, although only those pixels within the bounds of the project will be seen if the image is
saved.
This class has methods for changing it’s pixel’s filtered colors based on red, blue and green
filtering, along with brightening or darkening by values, intensities or lumas.

Pixel:
This class represents a single pixel in an image. It has a red value, a green value, a blue value,
an alpha intensity, and a resultant 3-channel RBG color from all of those values. The red, green,
blue and alpha values must all be between 0 and 255.
This class has methods for filtering the pixels to only show red values or green values or blue
values.
It also has methods for darkening or brightening the pixel’s reds, greens and blues by a specified
value, intensity of luma.
It also has methods for adding the values of one pixel to the top of the other, with specific math
for handling opacity from alpha values.

Classes (View Package)

CollageTextView:
This class is a view for monitoring output streams for testing purposes. It has the same methods as
CollageView. It holds an Appendable to append output messages to from the controller.

GUIView2
This class is a view for monitoring output streams for testing purposes. It has the same methods as
GUIView2, with additional methods for displaying images, adding layer buttons, and returning the
view frame. It holds 15 private JButtons, JPanels, JFrames, JLabels and JScrollPanes that comprise
the visual representation of the Collaging GUI for the user to interact with.


Script of Commands

Create a new 100 pixel by 100 pixel project with the following command line prompt:

`new-project 100 100
`

Add an additional layer labelled “layer-2” with the following command line prompt:

`add-layer layer-2`

Add an image with the specified filepath to the first layer of the project, offset by 5 pixels right
and 5 pixels down, with the following command line prompt:

`add-image layer-1 /Collaging/src/res/cherryNew.ppm`

Filter out all colors except green on layer-1 with the following command line prompt:

`set-filter layer-1 green-component`

Save the image of the project at a specific file location and with a specific name and extension using the following command line prompt:

`save-image Collaging/src/ wawa10.ppm`

Save the project at a specific file location with the following command line prompt:

`save-project Collaging/src/project.txt`

ImageReference:
`https://www.seekpng.com/ipng/u2e6r5r5o0r5u2t4_png-50-px-icon/`
