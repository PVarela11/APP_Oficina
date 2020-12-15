package com.example.safecar.model

data class Oficina(
        val nome: String,
        val descricao: String,
        val disponibilidade: Int,
        val estrelas: Int,
        val oficinaIMG: String
)

class OficinaBuilder {
    var nome: String = ""
    var descricao: String = ""
    var disponibilidade: Int = 0
    var estrelas: Int = 0
    var oficinaIMG: String = ""

    fun build() : Oficina = Oficina(nome, descricao, disponibilidade, estrelas, oficinaIMG)
}

fun oficina(block: OficinaBuilder.() -> Unit): Oficina = OficinaBuilder().apply(block).build()

fun fakeOficinas():MutableList<Oficina> = mutableListOf(
        oficina {
            nome = "Oficina do Sr José"
            descricao = "Descrição teste da oficina do senhor josé, seja muito bem vindo"
            disponibilidade = 2
            estrelas = 3
            oficinaIMG = "oficina2"
        },
        oficina {
            nome = "Oficina do Sr João"
            descricao = "Descrição teste da oficina do senhor João, seja muito bem vindo"
            disponibilidade = 1
            estrelas = 5
            oficinaIMG = "oficina1"
        },
        oficina {
            nome = "Oficina do Sr António"
            descricao = "Descrição teste da oficina do senhor antónio, seja muito bem vindo"
            disponibilidade = 0
            estrelas = 1
            oficinaIMG = "oficina1"
        },
        oficina {
            nome = "Oficina do Sr Armando"
            descricao = "Descrição teste da oficina do senhor armando, seja muito bem vindo"
            disponibilidade = 2
            estrelas = 2
            oficinaIMG = "oficina3"
        },

        oficina {
            nome = "Oficina do Sr António"
            descricao = "Descrição teste da oficina do senhor antónio, seja muito bem vindo"
            disponibilidade = 0
            estrelas = 1
            oficinaIMG = "oficina2"
        },
        oficina {
            nome = "Oficina do Sr Armando"
            descricao = "Descrição teste da oficina do senhor armando, seja muito bem vindo"
            disponibilidade = 2
            estrelas = 2
            oficinaIMG = "oficina3"
        },
        oficina {
            nome = "Oficina do Sr António"
            descricao = "Descrição teste da oficina do senhor antónio, seja muito bem vindo"
            disponibilidade = 0
            estrelas = 1
            oficinaIMG = "oficina2"
        },
        oficina {
            nome = "Oficina do Sr Armando"
            descricao = "Descrição teste da oficina do senhor armando, seja muito bem vindo"
            disponibilidade = 2
            estrelas = 2
            oficinaIMG = "oficina3"
        },
        oficina {
            nome = "Oficina do Sr Armando"
            descricao = "Descrição teste da oficina do senhor armando, seja muito bem vindo"
            disponibilidade = 2
            estrelas = 2
            oficinaIMG = "oficina3"
        },
        oficina {
            nome = "Oficina do Sr António"
            descricao = "Descrição teste da oficina do senhor antónio, seja muito bem vindo"
            disponibilidade = 0
            estrelas = 1
            oficinaIMG = "oficina2"
        },
        oficina {
            nome = "Oficina do Sr Armando"
            descricao = "Descrição teste da oficina do senhor armando, seja muito bem vindo"
            disponibilidade = 2
            estrelas = 2
            oficinaIMG = "oficina3"
        }
)