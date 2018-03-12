# Download

* [JAR File](/ChameleonFantasies_v1.jar)
* [Source Files](/v1.zip)

---
# Instructions

1. Download JAR file.
2. Run "ChameleonFantasies_v1.jar"

---
# Alternative way to run project

1. Download source files and extract them.
2. Create a [new project] and paste all files in the [new project] folder
3. Run the file
   * "src/negocios/fachada/TesteUsandoFachada.java" for a test without the GUI.
   * "src/negocios/gui/ChameleonFantasies.java" for a test with the GUI.

---
# Warning

  When the project v1 was created, we used Jakarta POI to save the information when saving in files (.xls). This library is very bad, full of deprecated functions and to make Jakarta work, the code would need to be modified so, we just removed all things related to Jakarta from the project.

  But, hey, what does this mean?

  This mean that when you close the application, all information will be lost. Do not worry. In v2 the only repository used was JSON files, meaning that information can be saved.
