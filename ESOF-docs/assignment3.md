## Development View

There are three main project libraries that are used in almost all of the others components: the Core library, the Structure library and the Alignment Library.


The core library interfaces with the other components (e.g.: sequencing library, structure library, alignment library) allowing those libraries to access standard proteins and nucleotide sequences. The structure and alignment libraries allow the other components to have access to structure and alignment tools, respectively.


Then there are another three external libraries - MMTF, VecMath and Forester - that are used by a significant part of the components. The MMTF is a specific format for binary encoding of biological structures, so the MMTF libraries provide tools for decoding and coding on this format. The VecMath allows the libraries that interface with it to use tools for mathematical calculations with arrays. The forester API provides tools for comparison of proteins and nucleotide sequences.


Mod Finder sequencing, Disorder predictor, Genome and AminoAcid properties libraries interface with some of the previously described components whose interfaces have also been previously described.


Then, one of the most important components to the user is the Structure GUI, which is a tool for 3D visualization of the proteins and its alignment (described in Section Process View). So, with this purpose, it interfaces the JMOL API that provides tools for representation of chemical structures in 3D, allowing the Structure GUI to show the 3D visualization of the proteins. Additionally, it interfaces with JColorBrewer that provides pallets of colour to the Structure GUI.


Finally, the project contains a web service that allows access to two different bioinformatics services in the web using the REST protocol, the NCBI Blast and the Hammer web service. So this component is dependent of these two services.


It was decided that it would be best to divide the component diagram in two parts due to the high amount of interfaces between components caused by the complexity of the project. The two parts of the diagram are the following:

![Component diagram](Images/component_model_1.jpg)
![Component diagram](Images/component_model_2.jpg)