package fetch

import chisel3._
import chisel3.util._
import chisel3.util.experimental.loadMemoryFromFile
import common.Consts._

/* ImemPortIoクラスはBundleを継承して、addrとinstの2信号を束ねる
   addr:メモリアドレスの入力
   inst:命令データの出力
   ともに32bit幅
 */

class ImemPortIo extends Bundle {
  val addr = Input(UInt(WORD_LEN.W))
  val inst = Output(UInt(WORD_LEN.W))
}

class Memory extends Module {
  val io = IO(new Bundle {
    val imem = new ImemPortIo()
  })

  /* メモリの実体として8bit×16384本(=16KB)のレジスタを生成
     8bit幅なのはPCが4ずつインクリメントすることに由来
     1アドレスに8bit、4アドレスに32bit(=信号線)格納される
   */
  val mem = Mem(16384, UInt(8.W))

  // メモリデータをロード
  loadMemoryFromFile(mem, "src/hex/fetch.hex")

  // 各アドレスに格納された8bitをまとめて32bitデータに
  io.imem.inst := Cat(
    mem(io.imem.addr + 3.U(WORD_LEN.W)),
    mem(io.imem.addr + 2.U(WORD_LEN.W)),
    mem(io.imem.addr + 1.U(WORD_LEN.W)),
    mem(io.imem.addr)
  )
}
