Chisel による自作CPUの実装
=======================
# Installation
1. Docker Desktopをインスト―ル
2. Docker Imageをpull
```docker
docker pull yutaronishiyama/riscv-chisel-book
```
3. レポをclone (zipをダウンロードして解凍も可能)
4. wsl上の `wsl:~/workspace/chisel` に配置
5. docker run　
```docker
docker run -it --rm --name chisel -v ~/workspace/chisel:/src --workdir /src chisel-book
```

# 参考文献
参考: [Chisel template](https://github.com/freechipsproject/chisel-template), [riscv-chisel-book](https://github.com/chadyuu/riscv-chisel-book)
