import React, { Component } from 'react'
import ReactCam from './ReactCam';

const path = require('path');
const express = require('express');
const http = require('http');
const WebSocket = require('ws');
const app = express();
const httpServer = http.createServer(app);

class LivestreamWebSocket extends Component {
    constructor(props) {
        super(props);

        this.state = {
            ws: null
        };
    }

    // single websocket instance for the own application and constantly trying to reconnect.

    componentDidMount() {
        this.connect();
    }

    timeout = 250; // Initial timeout duration as a class variable

    /**
     * @function connect
     * This function establishes the connect with the websocket and also ensures constant reconnection if connection closes
     */
    connect = () => {
        // const PORT = process.env.PORT || 3000;
        const PORT = 3001;

        const ws = new WebSocket.Server({ port:3001, server: httpServer }, () => console.log(`WS server is listening at ws://localhost:${PORT}`));

        // array of connected websocket clients
        let connectedClients = [];

        ws.on('connection', (ws, req) => {
            console.log('Connected');
            // add new connected client
            connectedClients.push(ws);
            // listen for messages from the streamer, the clients will not send anything so we don't need to filter
            ws.on('message', data => {
                // send the base64 encoded frame to each connected ws
                connectedClients.forEach((ws, i) => {
                    if (ws.readyState === ws.OPEN) { // check if it is still connected
                        ws.send(data); // send
                    } else { // if it's not connected remove from the array of connected ws
                        connectedClients.splice(i, 1);
                    }
                });
            });
        });

        // HTTP stuff
        app.get('/client', (req, res) => res.sendFile(path.resolve(__dirname, './client.html')));
        app.get('/streamer', (req, res) => res.sendFile(path.resolve(__dirname, './streamer.html')));
        app.get('/', (req, res) => {
        res.send(`
            <a href="streamer">Streamer</a><br>
            <a href="client">Client</a>
        `);
        });
        httpServer.listen(PORT, () => console.log(`HTTP server listening at http://localhost:${PORT}`));


        // // websocket onclose event listener
        // ws.onclose = e => {
        //     console.log(
        //         `Socket is closed. Reconnect will be attempted in ${Math.min(
        //             10000 / 1000,
        //             (that.timeout + that.timeout) / 1000
        //         )} second.`,
        //         e.reason
        //     );

        //     that.timeout = that.timeout + that.timeout; //increment retry interval
        //     connectInterval = setTimeout(this.check, Math.min(10000, that.timeout)); //call check function after timeout
        // };

        // websocket onerror event listener
        // ws.onerror = err => {
        //     console.error(
        //         "Socket encountered error: ",
        //         err.message,
        //         "Closing socket"
        //     );

        //     ws.close();
        // };
    };

    /**
     * utilited by the @function connect to check if the connection is close, if so attempts to reconnect
     */
    // check = () => {
    //     const { ws } = this.state;
    //     if (!ws || ws.readyState === WebSocket.CLOSED) this.connect(); 
    //check if websocket instance is closed, if so call `connect` function.
    // };

    render() {
        return <ReactCam websocket={this.state.ws} />;
    }
}

export default LivestreamWebSocket;