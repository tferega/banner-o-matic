#!/bin/bash

echo Starting Lift, firing up Jetty ...
`dirname $0`/sbt.sh --no-pause --loop "$@" ~lift