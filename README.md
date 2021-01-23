# Snowden SNLP Project

## Execution Procedure for Eclipse IDE:
- Open `File > Open Projects from File System`, choose the directory to import the project and press Finish.
- Project will be loaded in Eclipse.
- Go to `Window -> Preferences -> Expand General` and click `Workspace`, text file encoding (near bottom) has an encoding chooser. Select `"Other" radio button -> Select UTF-8` from the drop down and Click `Apply and OK` button OR simply click `OK`. ![here](https://i.stack.imgur.com/YKGdS.png)
- Go to `Main.java` class under `com.upb.snowden` package, run the file as Java application.
- The output `SNLP2020_test_output.ttl` file will be generated in `src/main/resources` directory after refreshing the project.

## Execution Procedure for Intellij IDE:
- Load the project as a Maven Project in Intellij IDE.
- Build project and Run `main` method in `Main.java`.
- The output `SNLP2020_test_output.ttl` file will be generated in `/resources` directory.

### Note 
- To enable logs, configure `isLoggingEnabled = true` in `main/java/utils/Logger.java`.
- To run training data, uncomment the `processTrainData()` method in `Main.java`.
