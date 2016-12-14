#Software Maintenance/Evolution

## Software Maintainability using the SIG Metrics
Since it is a quite complex project composed by more than 100.000 lines of code, two sub repositories had to be made in order to analyze it with Better Code Hub. For that reason, we runned Better Code Hub twice, in order to understand the differences that would appear when using each of the sub repositories. First it was chosen the part of the repository that included several demonstrations with a graphical user interface already used in the previous assignment: Structure GUI. For the second sub repository we included the same as in the first one, plus one of the modules that it had a dependency on: the Core module. The results can be seen [here](https://github.com/jalves94/biojava/tree/master/ESOF-docs/BetterCode_reports).

### Write Code Once
Don’t copy code. Even if it looks easier, the copied code can lead to duplicates. If those duplicates have bugs, they will be harder to fix since the code has to be fixed in more than one place and more errors can be made on the correction process. Another problem of duplicates is analyzing and detecting the source of the problem when a bug occurs in that part of the code.
The packages used for analysis have less than 95,4% of non duplicated code so it means that the project has a lot of redundant code and consequently its packages have a lot of copied code. 
To avoid this practice, the methods that are needed in more than one part of the code should be placed in one method that can be called wherever it is needed in the project. If the code is from another project, the necessary features should be imported and re-written instead of being copied, as that way it will be easier to understand and change in case of an error.
In conclusion, it would be a good practice to review and rewrite the code from at least the two analyzed packages by trying to understand which parts should be changed to facilitate the understanding of the code. This fact can be a problem in attracting new contributors to his open-source project.

### Keep Unit Interfaces Small
Unit interfaces are parameters that, if presented on high number, make the code hard to reuse, understand and, consequently, maintain. For that reason, it is good practice to have most methods with one, two, three or four parameters and, if possible, to group variables into an object.
In the _structure-gui_ and _core_ packages, there are some methods that have more than 6 parameters and the percentage of the evaluated project that has at most two parameters does not reach 83%. For that reason, BioJava does not have a good grade on this metric. Thinking about the structure of an object and how it can be related to the rest of the code before introducing it is the best way to keep unit interfaces small.

### Separate Concerns in Modules
As already said in previous reports, each module of the project has its own function. However, the modules need each other to be able to perform. There are separate packages for a specific functionality. However, classes are really large and inside one particular package they don’t have separate concerns. Thus, a class can be called multiple times by an outside class. The number of calls of this kind is considered for this metric. 
Looking at the results, it is possible to see differences between the two considered analysis. Including just the _structure-gui_ package, this metric has a good grade, due to the fact that most of the classes are called no more than 10 times and just a few have more than 50 calls. However, with both _structure-gui_ and _core_ packages, there are more classes that are called more than 50 times, leading to a bad classification in this metric. This is due to the fact that with the _core_ package, there is no proportional increase of classes with few and many calls and classes have more than one functionality/responsibility. In order to prevent this type of situations (preventing classes from getting a "large class smell"), classes must be slipped. A better separation of concerns can lead to a controlled maintenance of the project since a small change in one class does not have a huge effect in others.

### Couple Architecture Components Loosely
This topic deals with dependencies on the component level. A component has to have its boundaries well defined and should be weakly dependent from the other components of the project. The previous topic approached the dependencies between modules, i.e. the classes from one module that are called in another one. Yet, this one will cover the exposure of modules inside a component with other modules from a different component. Weak dependencies between components will lead to an easy maintenance since changes on each component will only affect itself. 
In the BioJava project the components will be its packages. On the first analysis it will have one component, the _structure-gui_ package, and in the second one it will have also the _core_ package.
The results from the evaluation of these dependencies are good in both analysis. The percentage of modules from one package that have incoming dependencies from modules in other package (interface code) is lower than 14,2%. 
This result means that the maintainability of this project in relation to the dependencies between modules inside components is facilitated. So, changes in modules, i.e. on a top-class or interface, from one of those packages would be easily understood and fastly fixed. The fact that the modules inside the packages are isolated would facilitate the split of the maintenance of each one in different contributor teams.


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