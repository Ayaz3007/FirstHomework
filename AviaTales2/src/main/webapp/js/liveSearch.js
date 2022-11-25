function liveSearch() {
    return fetch("/json-cities")
        .then(response => {
            return response.json();
        }).then(cities => {

        })
}
