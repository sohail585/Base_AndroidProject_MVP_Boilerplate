# Base_AndroidProject_MVP_Boilerplate

An MVP Boilerplate to save users having to create the same project over from scratch every time! :)

# Dependencies used in the project:

This project uses RxJava 2.0 and Retrofit 2.0 for networking purposes.
It uses Room database fro persistence.
It uses Picasso for displayiong images.
It uses Lombok plugin to auto generate getters and setters.
It uses Crashlytics to report crashes that happens anywhere in the production directly to your email Id.
It uses com.android.support:design to support recyclerView, snackbars, and etc.

# What is MVP

  MVP stands for Model, View, Presenter. It can also include Interactor(For more segregation).
  
# Why is MVP used?

  MVP is used to write good Unit testable code. Unit testable code is written by segregating Views, Business logic, database and network operations.

# What are Presenters, Interactors
  Presenters:
  
    Presenters are the middleman for model and interactors.
  Interactors:
  
    Interactors are the ones that are responsible for network operations.
    
# Where are examples for interactors, presenters found in the project.

  It is found inside the "main" package under "modules" section. (File Path : app -> java -> com.thbs.mis.baseboilerplateandroid -> modules -> main)
  
# How to use:

1. clone the project.
2. Change the package name. :

  Note :
  
  If you face any problems after renaming the package, Please refer this link : https://stackoverflow.com/questions/26888762/after-renaming-package-name-in-android-studio-launching-error-runtime-error
  
3. Make sure you create new modules under "modules" package(As of now, in this project, there is a module by the name "main" - which simply means main module).
4. All the modules must have contract, interactor, presenter, model, view packages.
  i). contract package will have an interface which in turn will have 3 other interfaces bundled in it, namely, View, Presenter, Interactor. All interfaces have to be extended from its base.
      For eg: View has to extend from BaseView, Presenter has to extend from BasePresenter, and Interactor has to extends from BaseInteractor.
 ii). interactor package will have all the interactors. All the interactors must have to implement its contract.Interactor interface.
      For eg: ProductInteractor will implement ProductContract.Interactor.
iii). presenter package will have all the presenters. All the presenters must have to implement its contract.Presenter interface.
      For eg: ProductPresenter will implement ProductContract.Presenter. 
 iv). model package will have all the models.
  v). view package will have all the activities, fragments and adapters.
      a) all the activities will extend BaseActivity and will implement its contract.View interface.
        For eg: ProductActivity will extend BaseActivity and will implement ProductContract.View.
      b) all the fragments will extend BaseFragment and will implement its contract.View interface.
        For eg: ProductActivity will extend BaseFragment and will implement ProductContract.View.
      

# What this project includes

1. Base classes for Activities, Adapters, Fragments.
2. Base interfaces to implement in Views, Presenters and Interactors.
3. Few UTIL classes that are required.

