#!/bin/bash
 
# 获取脚本所在的目录路径
script_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
# 切换到脚本所在的目录
cd "$script_dir"
java -jar recaf-2.21.13-J8-jar-with-dependencies.jar
