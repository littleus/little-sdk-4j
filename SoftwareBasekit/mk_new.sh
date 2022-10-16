#!/bin/bash

function main() {
  name=$1

  if [ -d ./org.littleus.sdk."$name" ]; then
    echo "dir already exists"
    return
  fi

  cp -R ./org.littleus.sdk.aaa ./org.littleus.sdk."$name"
  sed -i "s/littleus-sdk-aaa/littleus-sdk-${name}/g" ./org.littleus.sdk."$name"/pom.xml
  mv ./org.littleus.sdk."$name"/doc/littleus-sdk-aaa.md ./org.littleus.sdk."$name"/doc/littleus-sdk-"$name".md

}

main "$@"
