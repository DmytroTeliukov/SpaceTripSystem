let imagePlanet = document.getElementById('imagePlanet');
let planetSelect = document.getElementById('planetSelect');

function flip() {
    imagePlanet.src = planetSelect.children[planetSelect.selectedIndex].getAttribute('url');
}