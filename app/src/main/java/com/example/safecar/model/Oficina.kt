package com.example.safecar.model

data class Oficina(
        val id: String,
        val nome: String,
        val descricao: String,
        val disponibilidade: String,
        val estrelas: String,
        val oficinaIMG: String,
        val reboque: String,
        val morada: String
)

//class OficinaBuilder {
    //var nome: String = ""
    //var descricao: String = ""
    //var disponibilidade: String = ""
    //var estrelas: String = ""
    //var oficinaIMG: String = ""

    //fun build() : Oficina = Oficina(nome, descricao, disponibilidade, estrelas, oficinaIMG)
//}

//fun oficina(block: OficinaBuilder.() -> Unit): Oficina = OficinaBuilder().apply(block).build()

//fun fakeOficinas():MutableList<Oficina> = mutableListOf(
  //      oficina {
    //        nome = "Oficina do Sr José"
      //      descricao = "Descrição teste da oficina do senhor josé, seja muito bem vindo"
        //    disponibilidade = "2"
          //  estrelas = "3"
            //oficinaIMG = "oficina2"
        //},
        //oficina {
         //   nome = "Oficina do Sr João"
          //  descricao = "Descrição teste da oficina do senhor João, seja muito bem vindo"
           // disponibilidade = "1"
           // estrelas = "5"
           // oficinaIMG = "oficina1"
        //},
       // oficina {
        //    nome = "Oficina do Sr António"
         //   descricao = "Descrição teste da oficina do senhor antónio, seja muito bem vindo"
          //  disponibilidade = "0"
           // estrelas = "1"
            //oficinaIMG = "oficina1"
        //}
//)