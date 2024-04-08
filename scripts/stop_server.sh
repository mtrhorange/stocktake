#!/bin/sh
killall screen

kill -9 $(lsof -t -i tcp:8080)