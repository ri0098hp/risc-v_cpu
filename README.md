Chisel による自作CPUの実装
=======================
## Installation
1. Docker Desktopをインスト―ル
2. Docker Imageをpull
```docker
docker pull yutaronishiyama/riscv-chisel-book
```
3. Docker Imageのリネーム
```docker
docker tag yutaronishiyama/riscv-chisel-book chisel-book
```

4. レポをclone (zipをダウンロードして解凍も可能)
5. wsl上の `wsl:~/workspace/chisel` に配置
6. docker run　
```docker
docker run -it --name chisel -v ~/workspace/chisel:/src --workdir /src chisel-book
```
7. buildを実行
```bash
sbt "testOnly <package>.HexTest"
```
8. 初期化で処理が行われるので待機
9. 一旦exitしてコンテナをimageにcommitする
```bash
docker commit chisel chisel-book
```
これ以降は下の [Usage](#usage) に従ってビルドテストができるようになる. ただし [ここ](src/main/scala) にソースファイルを, [ここ](src/test/scala/) にテスト実行ファイルを置く必要がある.

## Usage
1. docker run　
```bash
docker run -it --rm --name chisel -v ~/workspace/chisel:/src --workdir /src chisel-book
```
2. bulid
```bash
sbt "testOnly <package>.HexTest"
```

# 参考文献
参考: [Chisel template](https://github.com/freechipsproject/chisel-template), [riscv-chisel-book](https://github.com/chadyuu/riscv-chisel-book)
