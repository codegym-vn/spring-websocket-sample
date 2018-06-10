function connect() {
    var socket = new SockJS('/users');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/users', function(response) {
            console.log(response);
            console.log(JSON.parse(response.body));
        });
    });
}
function disconnect() {
    stompClient.disconnect();
    console.log("Disconnected");
}
function send() {
    console.log("sending");
    var name = document.getElementById("name").value;
    stompClient.send("/app/user/"+name, {}, JSON.stringify({}));
}