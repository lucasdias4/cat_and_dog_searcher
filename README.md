# Cat and Dog searcher 


## Summary 
With this application you can search for breeds of dogs, cats or both at the same time. The application uses the Dog API and the Cat API to perform the searches.

## Screens 
<p align="center">
 <img src="/pictures/splash_dark.jpg" width=20% height=20%>
 <img src="/pictures/splash_default.jpg" width=20% height=20%>
</p>
<p align="center">
 <img src="/pictures/search_dark.jpg" width=20% height=20%>
 <img src="/pictures/search_default.jpg" width=20% height=20%>
</p>
<p align="center">
 <img src="/pictures/animal_list_dark.jpg" width=20% height=20%>
 <img src="/pictures/animal_list_default.jpg" width=20% height=20%>
</p>
</p>
<p align="center">
 <img src="/pictures/animal_detail_dark.jpg" width=20% height=20%>
 <img src="/pictures/animal_detail_default.jpg" width=20% height=20%>
</p>
<p align="center">
 <img src="/pictures/error_view_dark.jpg" width=18% height=18%>
 <img src="/pictures/error_view_loading_dark.jpg" width=18% height=18%>
 <img src="/pictures/success_without_content_default.jpg" width=18% height=18%>
</p>
<p align="center">
 <img src="/pictures/without_connection_default.jpg" width=18% height=18%>
 <img src="/pictures/with_connection_default.jpg" width=18% height=18%>
 <img src="/pictures/loading_view_default.jpg" width=18% height=18%>
</p>

## Automation
Ktlint - the task validates whether the code standard complies with the lint. Use the `./gradlew ktlint` command to validate.

KtlintFormat - this task modifies the code so that it follows the lint pattern. Use the `./gradlew ktlintFormat` command to adjust the code.

## Continuous Integration
**GitHub CI**

CI tool that allows the creation of customized workflows for repositories on GitHub.

**Workflows**

In this application, two workflows were created. The first for `Master` branche and the second for` featureas e fixes` branches. The following are descriptions of the workflows.

Master - run unit tests, Ktlint and APK generation.

Featureas e Fixes - run unit tests and Ktlint.

## Architecture
I tried to follow the concepts of Clean Architecture, so I divided the project into:

* **app module**: module that is started when user opens the application. It contains the SplashActivity, first screen of the app;

* **feature-modules**: contains the presentation layer of each feature (ex: feature-animal);

* **data module**: the data layer contains all the code necessary to retrieve the data, whether from a local database or a service;

* **domain module**: the business logic layer contains entities, use cases and interfaces to communicate with the data module;

* **core module**: it has some implementations that are used by the application (ex: connectivity handler, request handler, etc);

* **extension module**: this module has extensions that can be reused throughout the project;

* **resource module**: where colors, themes, fonts and icons are located. In this way, the entire application uses the same resources to maintain a standard in the project (facilitating the implementation of a Design System);

* **base module**: contains the base classes that are used by the application;

* **ui-component module**: The UI components were created to facilitate its reuse and to have a standard in the application (facilitating the implementation of a Design System);

* **buildSrc module**: is where all the dependencies and versions used for the project relies.

## Main dependencies
**Koin** - _dependence injection_
 <p> Library chosen for its simple implementation. As a negative point, there is some loss of performance when compared to other competitors, such as Dagger. There is no significant loss for this application. </p>

**Coroutines** - _dealing with threads and asynchronism_
 <p> Approach suggested by Google and working well with Live Data, makes good use of the device's Threads and Thread Pool, improving application performance. When compared to RxJava, which is its biggest competitor, its positive point is its smaller size and simplicity, its negative point is its error handling which is a little more manual. </p>

**Navigation component** - _navigation between screens_
 <p> Used as a new form of navigation for the application. </p>

**Retrofit** - _HTTP requirements_
 <p> Retrofit is the most widespread library for handling HTTP requests, in addition to being easy to implement. </p>
 
 **Shimmer** - _animation_
 <p> The Facebook library allows you to introduce shimmering animations in a simple way. It is widely used to signal the loading of some content. </p>
 
 **MockK** - _unit tests_
 <p> MockK is a test lib for the Kotlin language. Its use is simple and has the advantage of competitors, the possibility of mocking methods of Objects and Companion Objects without having to create interfaces. On the other hand, it is an exclusive library for Kotlin, not working with the Java language. </p>
 
## Tests
Because of the time I tested only one class but it was tested with 100% coverage.
* [unit test 1]( https://github.com/lucasdias4/cat_and_dog_searcher/blob/master/feature-animal/src/test/java/com/lucasdias/feature_animal/list/AnimalListViewModelTest.kt)

You can also visualizate other tests performed by me on an old project at the following links:
* [unit test 1]( https://github.com/lucasdias4/Marvel/blob/main/feature-comic/src/test/java/com/lucasdias/feature_comic/list/ComicListViewModelTest.kt)
* [unit test 2]( https://github.com/lucasdias4/chuck_norris_facts/blob/master/search/src/test/java/com/lucasdias/search/presentation/SearchViewModelTest.kt)
* [unit test 3]( https://github.com/lucasdias4/chuck_norris_facts/blob/master/factcatalog/src/test/java/com/lucasdias/factcatalog/FactCatalogAdapter.kt)
* [unit test 4]( https://github.com/lucasdias4/chuck_norris_facts/blob/master/search/src/test/java/com/lucasdias/search/domain/usecase/GetRandomCategoriesFromDatabaseTest.kt)
* [unit test 5]( https://github.com/lucasdias4/chuck_norris_facts/blob/master/search/src/test/java/com/lucasdias/search/data/historic/SearchHistoricRepositoryImplTest.kt)

<p>Note: to facilitate the creation of tests, I try to write functions with as little responsibility as possible and try to remove as much logic as possible from View.</p>

## Know problem and solutions
* <p>The end point used to search for animal breeds did not return an image but an image ID. To display the image, I brought the default URL of the images into the app and used the IDs. Ideally, the API would return the image URL and not the ID, so there is no risk of this URL being changed and due to backward compatibility, all old versions of the APP will stop showing images (as they will still be using the old URL);</p>
* <p>Some animal breeds arrived from the API without some information that I decided to display, such as life span, temperament, wikipedia link and image. In the case of the image, I display a replacement image when its request fails. In all other cases, I hide sections that have no content to show.</p>
