package com.jcranky.tdc2011

object Palestrante {
  val palestrante = "Paulo" + "JCranky"
  val meuBlog = "http://jcranky.com"
  val meuTwitter = "@jcranky"
  val meuEmail = "paulo.siqueira@gmail.com"
}

object Scala {
  val creator = "Martin Odersky" + "EPFL"
  val paradigmas = Array("OO", "Funcional")
  val exemplosDeUsuarios = Array("LinkedIn", "Twitter", "foursquare", "Sony Imageworks")
  val superPoderes = Array("roda na JVM", "Estaticamente Tipada")
}

object Referencias {
  val siteOficial = "http://www.scala-lang.org/"
  val listaEmPortugues = "http://groups.google.com/group/scala-br"
  val livros = Array("Beginning Scala - David Pollak", "Programming in Scala - Odersky et al")
  val apresentacao = "http://prezi.com/dfn4kezo6yjr/introducao-a-scala/"
}

class TDC2011 {
  val recursos = Map(
    atributos   -> List("val para valores",
                        "var para variaveis",
                        "tipos inferidos"),
    
    imports     -> List("multiplos em uma linha",
                        "todo o pacote com _",
                        "import com renomeaçao",
                        "em qualquer escopo"),
    
    exceptions  -> List("sintaxe com 'case'",
                        "multiplos 'catchs' de uma vez"),
    
    tuples      -> List("esse mapa usa tuplas!",
                        "permite juntar valores sem precisar criar uma classe nova",
                        "sintax sugar para legibilidade: valor1 -> valor2"),
    
    arrays      -> List("() ao inves de []",
                        "facilidades para criaçao com Array(x, y, ...)"),
    
    functions   -> List("declaraçao com def",
                        "Unit representa o retorno void",
                        "o valor retornado e a ultima linha executada da funcao",
                        "a chave neste mapa e uma funcao, declarada abaixo",
                        "anonimas 'in line'"),
    
    closures    -> List ("blocos de codigo!"),
   
    collections -> List("API poderosa, permite manipulacao funcional de colecoes",
                        "foreach",
                        "filter",
                        "map", 
                        "mkString",
                        "e tem muito mais, consultar API!"),
    
    implicits   -> List("transforma um tipo em outro, automaticamente",
                        "1 to 10 usa um implicit de Int para RichInt",
                        "specs transforma strings em especificacoes executaveis"),
  
    wrappedString -> List("implicit de String para WrappedString",
                          "de forma transparente, permite operar Strings como se fossem colecoes",
                          "e outras funcoes uteis..."),
  
    traits        -> List("brincando de compor classes"),
  
    caseClasses   -> List("equals, hashCode e toString",
                          "valores no construtor definem os atributos da classe",
                          "ganha factory via Apply"),
    
    patternMatch  -> List("parece com switch... mas muito mais poderoso",
                          "verifica qualquer tipo de valor",
                          "pode 'abrir' os valores de case classes"),
  
    actors        -> List("aborda concorrencia de forma diferente",
                          "entidades que trabalham isoladamente, ao inves de locks",
                          "recebem msgs, que ficam em seu mailbox, e processam uma a uma",
                          "API padrao basica, vale a pena olhar o framework Akka"),
  
    testing       -> List("varios frameworks e os java podem ser usados",
                          "specs / specs2 - uso esse bastante =D",
                          "ScalaTest"))
  
  def atributos: Unit = {
    var minhaVariavel = "Ola TDC"
    minhaVariavel = "Ola TDC 2011"
    
    val meuValor = "Scala no TDC 2011"
//    meuValor = "Scala no TDC 2012"
    
    val meuNumero = 99
  }
  
  def imports: Unit = {
    import java.util.Collection
    import java.util.{Set, List}
    import java.io.{FileReader => FReader}
    import java.net._
    
    println("imports!")
  }
  
  def exceptions: Unit = {
    import java.net._
    try {
      "adsf".toCharArray()(5)
      //new URL("jcranky.com")
    } catch {
      case e: MalformedURLException => println("erro na URL: " + e)
      case e => println("excessao: " + e)
    }
  }
  
  def tuples: Unit = {
    val nomeApelido = ("Paulo", "JCranky")
    val nomeApelido2 = "Paulo" -> "JCranky"
    
    nomeApelido.getClass
    nomeApelido2.getClass
  }
  
  def arrays: Unit = {
    val numeros = Array(1, 2, 3, 4, 5)
    println("numero 1: " + numeros(0))
  }
  
  def functions: Unit = {
    def functionInterna = println("funcoes podem ser declaradas em qualquer lugar")
    functionInterna
    
    def soma(x: Int, y: Int) = x + y
  }
  
  def closures: Unit = {
    println("agora algo mais complexo, fechamento automatico de recursos:")
    def using[A <: {def close(): Unit}, B](param: A)(f: A => B): B =
      try {
        f(param)
      } finally {
        param.close
      }
    
    import java.io._
    using(new BufferedReader(new FileReader("/home/jcranky/minicurso/text.txt"))) { reader =>
      var line = reader.readLine
      while (line != null) {
        println(line)
        line = reader.readLine
      }
    }
  }
  
  def collections: Unit = {
    val meusNumeros = List(1, 2, 3, 4, 5)
    
    meusNumeros.foreach(x => println(x))
    meusNumeros.foreach(println(_))
    meusNumeros.foreach(println)
    
    val numerosStr = meusNumeros.map("número " + _)
    numerosStr.foreach(println)
    
    val numerosPares = meusNumeros.filter(x => x % 2 == 0)
    numerosPares.foreach(println)
    
    val stringDeNumeros = meusNumeros.mkString("{", ",", "}")
    println(stringDeNumeros)
    
    println("Bonus Track !! Colecoes paralelas =D")
    meusNumeros.foreach( x => {
        Thread.sleep(1000)
        println(x)
      })
    meusNumeros.par.foreach( x => {
        Thread.sleep(1000)
        println(x)
      })
  }
  
  def implicits: Unit = {
    import java.io.File
    
    implicit def strToFile(path: String) = new File(path)
    val f: File = "/meu/arquivo"
    
    implicit def fileToStr(f: File) = f.getName
    val fileName: String = f
    
    1 to 10 foreach (println)
  }
  
  def wrappedString: Unit = {
    "TDC 2011" foreach(println)
    "TDC 2011" filter(_ isDigit)
    "TDC 2011" stripSuffix("11")
  }
  
  def traits: Unit = {
    println("ver traits.scala")
  }
  
  def caseClasses: Unit = {
    println("ver users.scala")
  }
  
  def patternMatch: Unit = {
    def printaTudo(x: Any) = x match {
      case 99 => println("numero 99!")
      case true => println("verdade")
      case false => println("mentira")
      case "jcranky" => println("www.jcranky.com - visitem! =D")
      case s: String => println("uma outra String qualquer")
      case _=> println("outra coisa qualquer")
    }
    
    println("no REPL, vemos ao vivo o comportamento do printaTudo =D")
    println("exemplo com case classes no users.scala")
  }
  
  def actors: Unit = {
    println("ver actors.scala")
  }
  
  def testing: Unit = {
    println("ver tests gerados pelo archetye maven")
  }
}
