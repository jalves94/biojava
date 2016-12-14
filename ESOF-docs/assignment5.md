#Software Maintenance/Evolution

## Feature Evolution

The _structure-gui_ package provides a lot of functionalities to visualize different proteins and its structure, ligands and also connexions. In the graphical user interface itself, there are some buttons and windows to select properties like color and style. Additionally, clicking with the right mouse button on the panel, a menu is shown with more properties and options: zooming, spinning, capturing as an image, recording a GIF, etc. However, using the right click is not intuitive at all since there are options that, as previously said, can be set through controls that are already visible when the user interface is opened. This can induce users to think that there are no more available options. To avoid this problem it would be easier if some of the most important options were available in the main window of the GUI.


Considering that zooming is one of the most important features to be able to visualize a protein in a better way, implementing a control to set the zoom is one of the features that we decided to evolve. Enhancing this feature means giving the user the opportunity to zoom in and out as many times as he wants inside a predefined range (0% until 500%). Additionally, spinning the protein also allows a better visualization of the structure, so we decided to add the possibility to start and stop the spinning of the protein.


Looking at the code, it is relatively easy to understand that the modifications necessary to implement the features have to be done in the class where the interface is defined, along with the Jmol panel (Jmol is the library used for the 3D visualization of the protein). Analysing one of the demos of the _structure-gui_ package (`DemoCE.java`), it can be seen that the following instruction is performed:


```java 
StructureAlignmentDisplay.display(afpChain, ca1, ca2);
```

The `display` method returns an object of class `StructureAlignmentJmol` which is where the interface is defined. Therefore, this was the only class that needed to suffer modifications, as the implemented changes don’t interfere with any other part of the project (low change impact).


The `StructureAlignmentJmol` class uses a GUI widget toolkit for JAVA called Swing to implement the interface and its components. So, in order to implement the zoom feature, an object of class `JSlider` was used. Adding a _ChangeListener_ to the object, it is possible to detect when the user changes the position of the cursor on the bar and get the corresponding value. This value (between 0 and 500) is used to change the Jmol scripting command that is going to be executed (ex: "zoom 300"). Regarding the spinning feature, an object of class `JCheckBox` was created. Using an _ItemListener_, it is possible to distinguish whether the box is checked or not, and when it is, the protein starts spinning by executing the corresponding Jmol command "spin ON". On the contrary, when the box is unchecked, the executed command is "spin OFF".


The appearance of the interface after the implementation of the features is the following:


![zoom100](Images/zoom100.png)


![zoom250](Images/zoom250.png)