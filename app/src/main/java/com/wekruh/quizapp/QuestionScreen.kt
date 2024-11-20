package com.wekruh.quizapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.wekruh.quizapp.databinding.QuestionScreenBinding
import kotlin.random.Random
import android.os.Handler
import android.os.Looper

class QuestionScreen : AppCompatActivity() {
    private lateinit var binding: QuestionScreenBinding
    private lateinit var sharedPref: SharedPreferences
    private var currentQuestionNumber = 1
    var wrong = 0

    override fun onStart() {
        sharedPref = getSharedPreferences("com.wekruh.quizapp", MODE_PRIVATE)

        binding.gameOver.visibility = View.INVISIBLE
        super.onStart()
    }

    private val questions = arrayOf(
        "What is the capital of France?",
        "Which planet is known as the Red Planet?",
        "What is the largest ocean on Earth?",
        "Who wrote 'To Kill a Mockingbird'?",
        "What is the chemical symbol for gold?",
        "Which element is the primary component of the sun?",
        "What year did the Titanic sink?",
        "Who painted the Mona Lisa?",
        "What is the hardest natural substance on Earth?",
        "What is the smallest prime number?",
        "What is the largest continent by area?",
        "Which element has the chemical symbol 'Na'?",
        "In which country would you find the Great Wall?",
        "Who is known as the Father of Computer Science?",
        "Which organ is vital for pumping blood through the human body?",
        "What is the capital city of Canada?",
        "Which planet is closest to the Sun?",
        "Who wrote 'Pride and Prejudice'?",
        "Which country is famous for the Eiffel Tower?",
        "What is the largest mammal in the ocean?",
        "Which is the smallest country in the world by land area?",
        "What is the freezing point of water in Celsius?",
        "Who painted the Sistine Chapel ceiling?",
        "What is the hardest natural substance on Earth?",
        "Which country is known as the Land of the Rising Sun?",
        "What is the most abundant gas in the Earth's atmosphere?",
        "Who discovered penicillin?",
        "Which planet is known as the 'Gas Giant'?",
        "What is the main ingredient in guacamole?",
        "Which is the longest river in the world?",
        "Which metal is liquid at room temperature?",
        "Who developed the theory of relativity?",
        "Which is the most spoken language in the world?",
        "What is the smallest country in Europe?",
        "What is the tallest mountain in the world?",
        "Who was the first person to walk on the moon?",
        "Which country is known for the Taj Mahal?",
        "Which is the largest desert in the world?",
        "What is the chemical symbol for iron?",
        "Which artist cut off his own ear?",
        "Who wrote '1984'?",
        "What is the largest planet in the Solar System?",
        "Which element has the atomic number 1?",
        "What is the capital of Japan?",
        "Who wrote 'The Catcher in the Rye'?",
        "What is the boiling point of water in Celsius?",
        "What is the main ingredient in traditional hummus?",
        "Which country is known as the Land of the Pharaohs?",
        "Who was the first President of the United States?",
        "Which planet is known as Earth's Twin?",
        "Who is the founder of Microsoft?",
        "Which country has the most islands?",
        "What is the national flower of Japan?",
        "What is the most populous city in the world?",
        "Which element is diamond made of?",
        "Who painted 'The Starry Night'?",
        "What is the currency of Japan?",
        "Which country is the largest producer of coffee?",
        "What is the smallest bone in the human body?",
        "Which organ in the human body detoxifies blood?",
        "Who discovered the law of gravity?",
        "Which is the longest bone in the human body?",
        "Which country is known as the Land Down Under?",
        "What is the largest country by land area?",
        "Who wrote 'The Great Gatsby'?",
        "Which element is used in pencils?",
        "Which is the most abundant element in the universe?",
        "Which organ in the human body is responsible for filtering blood?",
        "Who invented the telephone?",
        "Which is the fastest land animal?",
        "Which is the smallest ocean in the world?",
        "What is the capital of Italy?",
        "Who is the Greek god of the sea?",
        "What is the chemical symbol for potassium?",
        "Who wrote 'The Odyssey'?",
        "What is the hardest bone in the human body?",
        "Which organ is responsible for vision?",
        "What is the largest island in the world?",
        "Who was the first woman to win a Nobel Prize?",
        "Which planet has the most moons?",
        "What is the currency of the United Kingdom?",
        "Which country is home to the kangaroo?",
        "Who painted 'The Persistence of Memory'?",
        "What is the capital of Egypt?",
        "Which planet has the shortest day?",
        "Who discovered radium?",
        "What is the most consumed beverage in the world?",
        "What is the highest-grossing film of all time?",
        "Which country invented paper?",
        "What is the most common blood type?",
        "Which is the most widely eaten fish in the world?",
        "Who invented the World Wide Web?",
        "Which organ is primarily responsible for producing insulin?",
        "Which country is the largest producer of rice?",
        "Who developed the first successful polio vaccine?",
        "What is the most abundant mineral in the human body?",
        "What is the chemical symbol for oxygen?",
        "Which country has the longest coastline?",
        "Which planet is known as the 'Morning Star'?",
        "Who is the author of 'Harry Potter'?",
        "What is the capital of Australia?",
        "What is the tallest building in the world?",
        "Which country is known for the Great Barrier Reef?",
        "Who wrote 'The Hobbit'?",
        "What is the chemical symbol for silver?",
        "Which planet is known as the Blue Planet?",
        "What year did World War II end?",
        "Who painted 'The Last Supper'?",
        "What is the largest island in the Mediterranean Sea?",
        "What is the smallest country in South America?",
        "What is the largest desert in Africa?",
        "Which element has the chemical symbol 'He'?",
        "In which country would you find the Eiffel Tower?",
        "Who is known as the Father of Modern Physics?",
        "Which organ is responsible for filtering blood in the human body?",
        "What is the capital city of Australia?",
        "Which planet is known as the Morning Star?",
        "Who wrote 'Moby-Dick'?",
        "Which country is famous for the Colosseum?",
        "What is the largest mammal on land?",
        "Which is the smallest continent by land area?",
        "What is the boiling point of water in Fahrenheit?",
        "Who painted 'Starry Night'?",
        "What is the hardest natural substance on Earth?",
        "Which country is known as the Land of the Midnight Sun?",
        "What is the most abundant element in the Earth's crust?",
        "Who discovered the structure of DNA?",
        "Which planet is known as the Ice Giant?",
        "What is the main ingredient in sushi?",
        "Which is the longest river in Africa?"
    )

    private val choices = arrayOf(
        listOf("Berlin", "Madrid", "Paris", "Rome"),  //1
        listOf("Earth", "Mars", "Jupiter", "Saturn"),  //2
        listOf("Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"),  //3
        listOf("Harper Lee", "Mark Twain", "J.K. Rowling", "Ernest Hemingway"),  //4
        listOf("Ag", "Au", "Fe", "Hg"),  //5
        listOf("Helium", "Hydrogen", "Oxygen", "Carbon"),  //6
        listOf("1905", "1912", "1898", "1923"),  //7
        listOf("Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Claude Monet"),  //8
        listOf("Gold", "Iron", "Diamond", "Platinum"),  //9
        listOf("0", "1", "2", "3"),  //10
        listOf("Africa", "Asia", "Europe", "North America"),  //11
        listOf("Sodium", "Nitrogen", "Neon", "Nickel"),  //12
        listOf("Japan", "South Korea", "China", "Mongolia"),  //13
        listOf("Alan Turing", "Charles Babbage", "Ada Lovelace", "John von Neumann"),  //14
        listOf("Brain", "Liver", "Heart", "Lungs"),  //15
        listOf("Toronto", "Vancouver", "Montreal", "Ottawa"),  //16
        listOf("Venus", "Earth", "Mars", "Mercury"),  //17
        listOf("Jane Austen", "Emily Brontë", "Charles Dickens", "George Eliot"),  //18
        listOf("France", "Italy", "Spain", "Germany"),  //19
        listOf("Shark", "Dolphin", "Blue Whale", "Seal"),  //20
        listOf("Monaco", "Vatican City", "San Marino", "Liechtenstein"),  //21
        listOf("0°C", "32°C", "100°C", "-10°C"),  //22
        listOf("Leonardo da Vinci", "Raphael", "Michelangelo", "Caravaggio"),  //23
        listOf("Gold", "Iron", "Diamond", "Platinum"),  //24
        listOf("China", "Japan", "South Korea", "Thailand"),  //25
        listOf("Oxygen", "Carbon Dioxide", "Nitrogen", "Hydrogen"),  //26
        listOf("Louis Pasteur", "Alexander Fleming", "Edward Jenner", "Joseph Lister"),  //27
        listOf("Mars", "Earth", "Jupiter", "Venus"),  //28
        listOf("Tomato", "Avocado", "Pepper", "Onion"),  //29
        listOf("Amazon River", "Nile River", "Yangtze River", "Mississippi River"),  //30
        listOf("Mercury", "Iron", "Lead", "Bromine"),  //31
        listOf("Isaac Newton", "Albert Einstein", "Nikola Tesla", "Galileo Galilei"),  //32
        listOf("English", "Mandarin", "Spanish", "Hindi"),  //33
        listOf("Vatican City", "Monaco", "Liechtenstein", "San Marino"),  //34
        listOf("K2", "Everest", "Kilimanjaro", "Denali"),  //35
        listOf("Buzz Aldrin", "Neil Armstrong", "Yuri Gagarin", "John Glenn"),  //36
        listOf("India", "Turkey", "Egypt", "China"),  //37
        listOf("Sahara", "Gobi", "Arabian", "Antarctic"),  //38
        listOf("Fe", "Cu", "Au", "Pb"),  //39
        listOf("Vincent van Gogh", "Claude Monet", "Pablo Picasso", "Salvador Dali"),  //40
        listOf("George Orwell", "Aldous Huxley", "Ray Bradbury", "J.D. Salinger"),  //41
        listOf("Earth", "Mars", "Jupiter", "Saturn"),  //42
        listOf("Oxygen", "Hydrogen", "Nitrogen", "Carbon"),  //43
        listOf("Seoul", "Beijing", "Tokyo", "Bangkok"),  //44
        listOf("J.D. Salinger", "F. Scott Fitzgerald", "Ernest Hemingway", "George Orwell"),  //45
        listOf("90°C", "100°C", "110°C", "120°C"),  //46
        listOf("Chickpeas", "Lentils", "Beans", "Peas"),  //47
        listOf("Egypt", "India", "Greece", "Italy"),  //48
        listOf("Thomas Jefferson", "George Washington", "Abraham Lincoln", "John Adams"),  //49
        listOf("Venus", "Mars", "Jupiter", "Mercury"),  //50
        listOf("Steve Jobs", "Bill Gates", "Mark Zuckerberg", "Larry Page"),  //51
        listOf("Philippines", "Indonesia", "Sweden", "Japan"),  //52
        listOf("Cherry Blossom", "Rose", "Lotus", "Tulip"),  //53
        listOf("Tokyo", "New York", "Shanghai", "Mumbai"),  //54
        listOf("Carbon", "Iron", "Gold", "Oxygen"),  //55
        listOf("Vincent van Gogh", "Claude Monet", "Pablo Picasso", "Salvador Dali"),  //56
        listOf("Yen", "Won", "Dollar", "Pound"),  //57
        listOf("Brazil", "Vietnam", "Colombia", "Ethiopia"),  //58
        listOf("Femur", "Stapes", "Clavicle", "Scapula"),  //59
        listOf("Liver", "Kidneys", "Lungs", "Heart"),  //60
        listOf("Galileo Galilei", "Isaac Newton", "Albert Einstein", "Nikola Tesla"),  //61
        listOf("Femur", "Tibia", "Fibula", "Humerus"),  //62
        listOf("Australia", "New Zealand", "South Africa", "Canada"),  //63
        listOf("Canada", "Russia", "China", "United States"),  //64
        listOf("Ernest Hemingway", "F. Scott Fitzgerald", "Mark Twain", "William Faulkner"),  //65
        listOf("Graphite", "Charcoal", "Lead", "Carbon"),  //66
        listOf("Hydrogen", "Oxygen", "Nitrogen", "Carbon"),  //67
        listOf("Kidneys", "Liver", "Heart", "Lungs"),  //68
        listOf("Alexander Graham Bell", "Thomas Edison", "Nikola Tesla", "Guglielmo Marconi"),  //69
        listOf("Cheetah", "Lion", "Tiger", "Leopard"),  //70
        listOf("Arctic", "Indian", "Pacific", "Southern"),  //71
        listOf("Rome", "Paris", "Berlin", "Madrid"),  //72
        listOf("Zeus", "Poseidon", "Hades", "Apollo"),  //73
        listOf("K", "Na", "Ca", "Mg"),  //74
        listOf("Homer", "Virgil", "Sophocles", "Euripides"),  //75
        listOf("Femur", "Skull", "Rib", "Spine"),  //76
        listOf("Heart", "Lungs", "Eyes", "Brain"),  //77
        listOf("Greenland", "Australia", "Madagascar", "New Guinea"),  //78
        listOf("Marie Curie", "Mother Teresa", "Jane Austen", "Florence Nightingale"),  //79
        listOf("Earth", "Saturn", "Jupiter", "Neptune"),  //80
        listOf("Dollar", "Pound", "Euro", "Yen"),  //81
        listOf("Australia", "New Zealand", "South Africa", "India"),  //82
        listOf("Pablo Picasso", "Salvador Dali", "Claude Monet", "Vincent van Gogh"),  //83
        listOf("Cairo", "Dubai", "Riyadh", "Amman"),  //84
        listOf("Earth", "Mars", "Jupiter", "Neptune"),  //85
        listOf("Albert Einstein", "Marie Curie", "Isaac Newton", "Nikola Tesla"),  //86
        listOf("Water", "Tea", "Coffee", "Coca-Cola"),  //87
        listOf("Avatar", "Avengers: Endgame", "Titanic", "Star Wars: The Force Awakens"),  //88
        listOf("China", "Egypt", "India", "Greece"),  //89
        listOf("O+", "A+", "B+", "AB+"),  //90
        listOf("Salmon", "Tuna", "Herring", "Cod"),  //91
        listOf("Tim Berners-Lee", "Bill Gates", "Steve Jobs", "Mark Zuckerberg"),  //92
        listOf("Pancreas", "Liver", "Kidneys", "Stomach"),  //93
        listOf("China", "India", "Indonesia", "Vietnam"),  //94
        listOf("Jonas Salk", "Alexander Fleming", "Edward Jenner", "Louis Pasteur"),  //95
        listOf("Calcium", "Iron", "Potassium", "Magnesium"),  //96
        listOf("O", "H", "N", "C"),  //97
        listOf("Canada", "Russia", "United States", "Australia"),  //98
        listOf("Venus", "Mars", "Mercury", "Jupiter"),  //99
        listOf("J.K. Rowling", "J.R.R. Tolkien", "Stephen King", "George R.R. Martin"),
        listOf("Sydney", "Melbourne", "Canberra", "Brisbane"),
        listOf("Burj Khalifa", "Shanghai Tower", "Abraj Al-Bait Clock Tower", "Ping An Finance Centre"),
        listOf("Australia", "Brazil", "Mexico", "South Africa"),
        listOf("J.R.R. Tolkien", "J.K. Rowling", "George Orwell", "Mark Twain"),
        listOf("Ag", "Au", "Si", "Hg"),
        listOf("Earth", "Mars", "Neptune", "Uranus"),
        listOf("1940", "1945", "1950", "1955"),
        listOf("Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Claude Monet"),
        listOf("Sicily", "Sardinia", "Cyprus", "Crete"),
        listOf("Suriname", "Guyana", "Uruguay", "Paraguay"),
        listOf("Sahara", "Kalahari", "Namib", "Gobi"),
        listOf("Helium", "Hydrogen", "Oxygen", "Carbon"),
        listOf("France", "Italy", "Spain", "Germany"),
        listOf("Albert Einstein", "Isaac Newton", "Galileo Galilei", "Nikola Tesla"),
        listOf("Liver", "Kidneys", "Heart", "Lungs"),
        listOf("Sydney", "Melbourne", "Canberra", "Brisbane"),
        listOf("Venus", "Mars", "Jupiter", "Mercury"),
        listOf("Herman Melville", "Mark Twain", "Charles Dickens", "Ernest Hemingway"),
        listOf("Italy", "Greece", "Spain", "France"),
        listOf("Elephant", "Blue Whale", "Giraffe", "Hippopotamus"),
        listOf("Australia", "Europe", "Antarctica", "South America"),
        listOf("200°F", "212°F", "220°F", "230°F"),
        listOf("Vincent van Gogh", "Claude Monet", "Pablo Picasso", "Salvador Dali"),
        listOf("Diamond", "Gold", "Iron", "Platinum"),
        listOf("Norway", "Sweden", "Finland", "Denmark"),
        listOf("Oxygen", "Silicon", "Aluminum", "Iron"),
        listOf("James Watson", "Francis Crick", "Rosalind Franklin", "All of the above"),
        listOf("Neptune", "Uranus", "Saturn", "Jupiter"),
        listOf("Rice", "Fish", "Seaweed", "Soy Sauce"),
        listOf("Nile", "Amazon", "Congo", "Zambezi")
    )

    private val correctAnswers = arrayOf(
        2,  // Paris
        1,  // Mars
        3,  // Pacific Ocean
        0,  // Harper Lee
        1,  // Au
        1,  // Hydrogen
        1,  // 1912
        2,  // Leonardo da Vinci
        2,  // Diamond
        1,  // 1
        1,  // Asia
        0,  // Sodium
        2,  // China
        0,  // Alan Turing
        2,  // Heart
        3,  // Ottawa
        3,  // Mercury
        0,  // Jane Austen
        0,  // France
        2,  // Blue Whale
        1,  // Vatican City
        0,  // 0°C
        2,  // Michelangelo
        2,  // Diamond
        1,  // Japan
        2,  // Nitrogen
        1,  // Alexander Fleming
        2,  // Jupiter
        1,  // Avocado
        1,  // Nile River
        0,  // Bromine
        1,  // Albert Einstein
        1,  // Mandarin
        0,  // Vatican City
        1,  // Everest
        1,  // Neil Armstrong
        0,  // India
        0,  // Sahara
        0,  // Fe
        0,  // Vincent van Gogh
        0,  // George Orwell
        2,  // Jupiter
        1,  // Hydrogen
        2,  // Tokyo
        0,  // J.D. Salinger
        1,  // 100°C
        0,  // Chickpeas
        0,  // Egypt
        1,  // George Washington
        0,  // Venus
        1,  // Bill Gates
        2,  // Sweden
        0,  // Cherry Blossom
        0,  // Tokyo
        0,  // Carbon
        0,  // Vincent van Gogh
        0,  // Yen
        0,  // Brazil
        1,  // Stapes
        0,  // Liver
        1,  // Isaac Newton
        0,  // Femur
        0,  // Australia
        1,  // Russia
        1,  // F. Scott Fitzgerald
        0,  // Graphite
        0,  // Hydrogen
        1,  // Liver
        0,  // Alexander Graham Bell
        0,  // Cheetah
        0,  // Arctic
        0,  // Rome
        0,  // Zeus
        1,  // Na (Sodium)
        0,  // Homer
        0,  // Femur
        3,  // Brain
        0,  // Greenland
        0,  // Marie Curie
        2,  // Jupiter
        2,  // Euro
        3,  // Australia
        3,  // Salvador Dali
        0,  // Cairo
        1,  // Mars
        2,  // Albert Einstein
        0,  // Water
        1,  // Avengers: Endgame
        0,  // China
        0,  // O+
        0,  // Salmon
        0,  // Tim Berners-Lee
        1,  // Pancreas
        1,  // India
        2,  // Jonas Salk
        0,  // Calcium
        0,  // Oxygen
        0,  // Canada
        2,  // Mercury
        1,  // J.R.R. Tolkien
        2,  // Canberra
        0,  // Burj Khalifa
        0,  // Australia
        0,  // J.R.R. Tolkien
        0,  // Ag
        0,  // Earth
        1,  // 1945
        2,  // Leonardo da Vinci
        0,  // Sicily
        2,  // Uruguay
        0,  // Sahara
        0,  // Helium
        0,  // France
        0,  // Albert Einstein
        0,  // Liver
        2,  // Canberra
        0,  // Venus
        0,  // Herman Melville
        0,  // Italy
        0,  // Elephant
        2,  // Antarctica
        1,  // 212°F
        0,  // Vincent van Gogh
        0,  // Diamond
        0,  // Norway
        0,  // Oxygen
        3,  // All of the above
        1,  // Uranus
        1,  // Fish
        0   // Nile
    )

    private val shownQuestions = mutableListOf<Int>()

    private fun getQuestionNum(): Int {
        if (shownQuestions.size >= questions.size) {
            Toast.makeText(this, "No more questions!", Toast.LENGTH_SHORT).show()
            return -1
        }

        var questionIndex: Int
        do {
            questionIndex = Random.nextInt(0, questions.size)
        } while (shownQuestions.contains(questionIndex))

        shownQuestions.add(questionIndex)
        return questionIndex
    }

    private fun applyQuestion(view: View) {
        val questionIndex = getQuestionNum()
        if (questionIndex == -1) return

        binding.questionText.text = questions[questionIndex]
        val questionChoices = choices[questionIndex]
        binding.btnA.text = questionChoices[0]
        binding.btnB.text = questionChoices[1]
        binding.btnC.text = questionChoices[2]
        binding.btnD.text = questionChoices[3]
        binding.root.tag = correctAnswers[questionIndex]

        binding.questionCounter.text = "Question: $currentQuestionNumber"
        currentQuestionNumber++
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = QuestionScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        applyQuestion(binding.root)

        binding.btnA.setOnClickListener { checkAnswer(0) }
        binding.btnB.setOnClickListener { checkAnswer(1) }
        binding.btnC.setOnClickListener { checkAnswer(2) }
        binding.btnD.setOnClickListener { checkAnswer(3) }
    }

    fun closeButton(view: View) {
        binding.btnA.isClickable = false
        binding.btnB.isClickable = false
        binding.btnC.isClickable = false
        binding.btnD.isClickable = false
    }

    private fun checkAnswer(selectedAnswerIndex: Int) {
        val correctAnswerIndex = binding.root.tag as Int
        if (selectedAnswerIndex == correctAnswerIndex) {
            Toast.makeText(this, "True", Toast.LENGTH_SHORT).show()
            applyQuestion(binding.root)
        } else {
            Toast.makeText(this, "False", Toast.LENGTH_SHORT).show()
            wrong += 1
            binding.wrongAnswers.text = binding.wrongAnswers.text.toString() + "X"
            if (wrong == 3) {
                closeButton(binding.root)
                binding.gameOver.visibility = View.VISIBLE
                binding.btnA.visibility = View.INVISIBLE
                binding.btnB.visibility = View.INVISIBLE
                binding.btnC.visibility = View.INVISIBLE
                binding.btnD.visibility = View.INVISIBLE
                binding.questionText.visibility = View.INVISIBLE
                binding.questionCounter.visibility = View.INVISIBLE
                binding.wrongAnswers.visibility = View.INVISIBLE
                Handler(Looper.getMainLooper()).postDelayed({
                    sharedPref.edit().putInt("lastTry",(currentQuestionNumber - 1)).apply()

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }, 3000)
            }
        }
    }
}