package fetch

import chisel3._
import common.Consts._

class Core extends Module {
  val io = IO(new Bundle {
    /* ImemPortIOをインスタンス化したものを反転
       つまり、出力ポートaddr、および入力ポートinstを生成 */
    val imem = Flipped(new ImemPortIo())

    // 出力ポートexitは処理が終わった時にtrueを返す
    val exit = Output(Bool())
  })

  // 32bit幅×32本のレジスタを生成
  val regfile = Mem(32, UInt(WORD_LEN.W))

  // *************************************************
  // Instruction Fetch (IF) Stage
  // PC(プログラムカウンタ)レジスタ(4ずつインクリメント)
  val pc_reg = RegInit(START_ADDR)
  pc_reg := pc_reg + 4.U(WORD_LEN.W)

  // 出力ポートaddrに接続、入力ポートをinstに接続
  io.imem.addr := pc_reg
  val inst = io.imem.inst

  // *************************************************
  // デバッグ
  // exit信号はinstが"34333231"(プログラムの最終行)の場合にtrueを返す
  io.exit := (inst === 0x34333231.U(WORD_LEN.W))
  printf(p"pc_reg : 0x${Hexadecimal(pc_reg)}\n")
  printf(p"inst   : 0x${Hexadecimal(inst)}\n")
  printf("---------\n")
}
