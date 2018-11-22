# movieDbApp
feauters:
The application named MovieApp, which supports Android API level 19 and above. This application will show information about movies shown in cinemas. The movie data that is being displayed is fetched from the API provided by https://www.themoviedb.org. The API documentation could be found on this URL https://developers.themoviedb.org/3 and you must register to get the API Key for your app.

This application will have two screens :

List of Movies

This screen displays the list of movies in form of list or grid. There are 3 categories of movies list that user could select (using tabs/navigation drawer) : Now Playing, Upcoming, and Popular. Each movie item displayed in the list/grid must at least contain the movie poster, movie title, and rating score (based on votes). The application must load the next page of data when user scrolls to the end of the list. And if user clicks an item in the list, it will display the Movie Detail screen for the selected movie.


Movie Detail

This screen displays the detail information of a movie. The information that must be available are :
-Movie title
-Movie poster
-Running time
-Release date
-Original Languages
-Rating score and number of votes

Some Data not found on the movie db api below:
-List of actor/actress (limit to 5 people) including the role name
-Synopsis
-Videos/Trailers (user should be able to play the video) - if exists
-only Genres ids exist


