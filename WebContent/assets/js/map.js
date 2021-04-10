var lat = 37.606991
var lng = 127.0232185
var zoom = 11

function initMap() {
    var opt = {
    // google map에 중앙으로 표시할 좌표 설정
		center: {
			lat: lat,
			lng: lng
			},
		zoom: zoom, //0~ 21  큰 값일 수록  zooming
    };

    //google.maps.Map(map을 그릴 영역, 옵션정보 );
    map = new google.maps.Map(document.getElementById("map"), opt);

    var infowindow = new google.maps.InfoWindow();

    var marker, i;
    for (i = 0; i < location.length; i++) {
    	marker = new google.maps.Marker({
    		id: i,
    		title: locations[i][0],
    		label: locations[i][0],
    		position: new google.maps.LatLng(locations[i][1], locations[i][2]),
    		map: map
    	});

    google.maps.event.addListener(marker, 'click', (function (marker, i) {
    	return function () {
    		infowindow.setContent(locations[i][0]);
    		infowindow.open(map, marker);
    	}
    })(marker, i));

    if (marker) {
    	marker.addListener('click', function () {
                console.log(this.title);
                map.setZoom(15);
                map.panTo(this.getPosition());
            });
        }
    }
}