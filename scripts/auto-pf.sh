#!/bin/bash

APP_PID_FILE_L="./pf-app-local.pid"
APP_PID_FILE_M="./pf-app-mac.pid"

GRAF_PID_FILE_L="./pf-grafana-local.pid"
GRAF_PID_FILE_M="./pf-grafana-mac.pid"

PROM_PID_FILE_L="./pf-prometheus-local.pid"
PROM_PID_FILE_M="./pf-prometheus-mac.pid"

APP_LABEL="app=asgn-09-java-app"
GRAF_SERVICE="monitoring-grafana"
PROM_SERVICE="monitoring-kube-prometheus-prometheus"

APP_PORT=5004
GRAF_PORT=5005
PROM_PORT=5006

while true; do

  APP_POD=$(kubectl get pods -l $APP_LABEL -o jsonpath="{.items[0].metadata.name}")

  if [ ! -z "$APP_POD" ]; then

    if [ -f "$APP_PID_FILE_L" ]; then
      kill -9 $(cat "$APP_PID_FILE_L") 2>/dev/null || true
    fi

    if [ -f "$APP_PID_FILE_M" ]; then
      kill -9 $(cat "$APP_PID_FILE_M") 2>/dev/null || true
    fi

    kubectl port-forward pod/$APP_POD $APP_PORT:8080 --address 127.0.0.1 &
    echo $! > "$APP_PID_FILE_L"

    kubectl port-forward pod/$APP_POD $APP_PORT:8080 --address 0.0.0.0 &
    echo $! > "$APP_PID_FILE_M"

  fi

  GRAFANA=$(kubectl get svc $GRAF_SERVICE -o jsonpath="{.metadata.name}")

  if [ ! -z "$GRAFANA" ]; then

    if [ -f "$GRAF_PID_FILE_L" ]; then
      kill -9 $(cat "$GRAF_PID_FILE_L") 2>/dev/null || true
    fi

    if [ -f "$GRAF_PID_FILE_M" ]; then
      kill -9 $(cat "$GRAF_PID_FILE_M") 2>/dev/null || true
    fi

    kubectl port-forward svc/$GRAF_SERVICE $GRAF_PORT:80 --address 127.0.0.1 &
    echo $! > "$GRAF_PID_FILE_L"

    kubectl port-forward svc/$GRAF_SERVICE $GRAF_PORT:80 --address 0.0.0.0 &
    echo $! > "$GRAF_PID_FILE_M"

  fi

  PROMETHEUS=$(kubectl get svc $PROM_SERVICE -o jsonpath="{.metadata.name}")

  if [ ! -z "$PROMETHEUS" ]; then

    if [ -f "$PROM_PID_FILE_L" ]; then
      kill -9 $(cat "$PROM_PID_FILE_L") 2>/dev/null || true
    fi

    if [ -f "$PROM_PID_FILE_M" ]; then
      kill -9 $(cat "$PROM_PID_FILE_M") 2>/dev/null || true
    fi

    kubectl port-forward svc/$PROM_SERVICE $PROM_PORT:9090 --address 127.0.0.1 &
    echo $! > "$PROM_PID_FILE_L"

    kubectl port-forward svc/$PROM_SERVICE $PROM_PORT:9090 --address 0.0.0.0 &
    echo $! > "$PROM_PID_FILE_M"

  fi

  sleep 15

done
