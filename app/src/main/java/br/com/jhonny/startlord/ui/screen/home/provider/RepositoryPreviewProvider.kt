package br.com.jhonny.startlord.ui.screen.home.provider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.com.jhonny.startlord.ui.screen.home.vo.RepositoryVO

internal class RepositoryPreviewProvider : PreviewParameterProvider<List<RepositoryVO>> {
    override val values: Sequence<List<RepositoryVO>>
        get() = sequenceOf(
            listOf(
                RepositoryVO(
                    id = 1,
                    name = "Lumos",
                    author = "Jhonatan",
                    starCount = 124,
                    forkCount = 37,
                    imageUrl = "https://picsum.photos/200?random=1"
                ),
            ),
            listOf(
                RepositoryVO(
                    id = 1,
                    name = "Lumos",
                    author = "Jhonatan",
                    starCount = 124,
                    forkCount = 37,
                    imageUrl = "https://picsum.photos/200?random=1"
                ),
            ),
            listOf(
                RepositoryVO(
                    id = 3,
                    name = "Orion",
                    author = "Carlos",
                    starCount = 321,
                    forkCount = 58,
                    imageUrl = "https://picsum.photos/200?random=3"
                ),
                RepositoryVO(
                    id = 31,
                    name = "NebulaCore",
                    author = "Henrique",
                    starCount = 1899,
                    forkCount = 399,
                    imageUrl = "https://picsum.photos/200?random=31"
                ),
            ),
            listOf(
                RepositoryVO(
                    id = 2,
                    name = "Nebula",
                    author = "Alice",
                    starCount = 982,
                    forkCount = 210,
                    imageUrl = "https://picsum.photos/200?random=2"
                ),
                RepositoryVO(
                    id = 31,
                    name = "NebulaCore",
                    author = "Henrique",
                    starCount = 1899,
                    forkCount = 399,
                    imageUrl = "https://picsum.photos/200?random=31"
                ),
                RepositoryVO(
                    id = 34,
                    name = "Apollo",
                    author = "Sofia",
                    starCount = 1390,
                    forkCount = 287,
                    imageUrl = "https://picsum.photos/200?random=34"
                ),
            ),
            listOf(
                RepositoryVO(
                    id = 2,
                    name = "Nebula",
                    author = "Alice",
                    starCount = 982,
                    forkCount = 210,
                    imageUrl = "https://picsum.photos/200?random=2"
                ),
                RepositoryVO(
                    id = 31,
                    name = "NebulaCore",
                    author = "Henrique",
                    starCount = 1899,
                    forkCount = 399,
                    imageUrl = "https://picsum.photos/200?random=31"
                ),
                RepositoryVO(
                    id = 34,
                    name = "Apollo",
                    author = "Sofia",
                    starCount = 1390,
                    forkCount = 287,
                    imageUrl = "https://picsum.photos/200?random=34"
                ),
                RepositoryVO(
                    id = 37,
                    name = "AtlasX",
                    author = "Bruno",
                    starCount = 341,
                    forkCount = 72,
                    imageUrl = "https://picsum.photos/200?random=37"
                ),
            ),
            listOf(
                RepositoryVO(
                    id = 2,
                    name = "Nebula",
                    author = "Alice",
                    starCount = 982,
                    forkCount = 210,
                    imageUrl = "https://picsum.photos/200?random=2"
                ),
                RepositoryVO(
                    id = 3,
                    name = "Orion",
                    author = "Carlos",
                    starCount = 321,
                    forkCount = 58,
                    imageUrl = "https://picsum.photos/200?random=3"
                ),
                RepositoryVO(
                    id = 4,
                    name = "Andromeda",
                    author = "Marina",
                    starCount = 1456,
                    forkCount = 330,
                    imageUrl = "https://picsum.photos/200?random=4"
                ),
                RepositoryVO(
                    id = 12,
                    name = "Atlas",
                    author = "Camila",
                    starCount = 332,
                    forkCount = 71,
                    imageUrl = "https://picsum.photos/200?random=12"
                ),
                RepositoryVO(
                    id = 31,
                    name = "NebulaCore",
                    author = "Henrique",
                    starCount = 1899,
                    forkCount = 399,
                    imageUrl = "https://picsum.photos/200?random=31"
                ),
            ),
            sample,
        )

    companion object {
        val sample = listOf(
            RepositoryVO(
                id = 1,
                name = "Lumos",
                author = "Jhonatan",
                starCount = 124,
                forkCount = 37,
                imageUrl = "https://picsum.photos/200?random=1"
            ),
            RepositoryVO(
                id = 2,
                name = "Nebula",
                author = "Alice",
                starCount = 982,
                forkCount = 210,
                imageUrl = "https://picsum.photos/200?random=2"
            ),
            RepositoryVO(
                id = 3,
                name = "Orion",
                author = "Carlos",
                starCount = 321,
                forkCount = 58,
                imageUrl = "https://picsum.photos/200?random=3"
            ),
            RepositoryVO(
                id = 4,
                name = "Andromeda",
                author = "Marina",
                starCount = 1456,
                forkCount = 330,
                imageUrl = "https://picsum.photos/200?random=4"
            ),
            RepositoryVO(
                id = 5,
                name = "Cosmos",
                author = "Rafael",
                starCount = 765,
                forkCount = 120,
                imageUrl = "https://picsum.photos/200?random=5"
            ),
            RepositoryVO(
                id = 6,
                name = "Eclipse",
                author = "Fernanda",
                starCount = 543,
                forkCount = 87,
                imageUrl = "https://picsum.photos/200?random=6"
            ),
            RepositoryVO(
                id = 7,
                name = "Nova",
                author = "Thiago",
                starCount = 2389,
                forkCount = 456,
                imageUrl = "https://picsum.photos/200?random=7"
            ),
            RepositoryVO(
                id = 8,
                name = "Aurora",
                author = "Beatriz",
                starCount = 876,
                forkCount = 142,
                imageUrl = "https://picsum.photos/200?random=8"
            ),
            RepositoryVO(
                id = 9,
                name = "Zenith",
                author = "Lucas",
                starCount = 412,
                forkCount = 63,
                imageUrl = "https://picsum.photos/200?random=9"
            ),
            RepositoryVO(
                id = 10,
                name = "Horizon",
                author = "Gabriela",
                starCount = 159,
                forkCount = 24,
                imageUrl = "https://picsum.photos/200?random=10"
            ),
            RepositoryVO(
                id = 11,
                name = "Quantum",
                author = "Pedro",
                starCount = 923,
                forkCount = 187,
                imageUrl = "https://picsum.photos/200?random=11"
            ),
            RepositoryVO(
                id = 12,
                name = "Atlas",
                author = "Camila",
                starCount = 332,
                forkCount = 71,
                imageUrl = "https://picsum.photos/200?random=12"
            ),
            RepositoryVO(
                id = 13,
                name = "Helix",
                author = "André",
                starCount = 1467,
                forkCount = 309,
                imageUrl = "https://picsum.photos/200?random=13"
            ),
            RepositoryVO(
                id = 14,
                name = "NebulaX",
                author = "Larissa",
                starCount = 542,
                forkCount = 98,
                imageUrl = "https://picsum.photos/200?random=14"
            ),
            RepositoryVO(
                id = 15,
                name = "Aether",
                author = "Rodrigo",
                starCount = 219,
                forkCount = 43,
                imageUrl = "https://picsum.photos/200?random=15"
            ),
            RepositoryVO(
                id = 16,
                name = "Ignis",
                author = "Juliana",
                starCount = 1278,
                forkCount = 267,
                imageUrl = "https://picsum.photos/200?random=16"
            ),
            RepositoryVO(
                id = 17,
                name = "Vortex",
                author = "Mateus",
                starCount = 391,
                forkCount = 76,
                imageUrl = "https://picsum.photos/200?random=17"
            ),
            RepositoryVO(
                id = 18,
                name = "Nimbus",
                author = "Isabela",
                starCount = 652,
                forkCount = 134,
                imageUrl = "https://picsum.photos/200?random=18"
            ),
            RepositoryVO(
                id = 19,
                name = "Lyra",
                author = "Felipe",
                starCount = 1043,
                forkCount = 205,
                imageUrl = "https://picsum.photos/200?random=19"
            ),
            RepositoryVO(
                id = 20,
                name = "Equinox",
                author = "Vanessa",
                starCount = 821,
                forkCount = 176,
                imageUrl = "https://picsum.photos/200?random=20"
            ),
            RepositoryVO(
                id = 21,
                name = "Stellar",
                author = "Eduardo",
                starCount = 312,
                forkCount = 54,
                imageUrl = "https://picsum.photos/200?random=21"
            ),
            RepositoryVO(
                id = 22,
                name = "Phoenix",
                author = "Patrícia",
                starCount = 1589,
                forkCount = 342,
                imageUrl = "https://picsum.photos/200?random=22"
            ),
            RepositoryVO(
                id = 23,
                name = "Draco",
                author = "Guilherme",
                starCount = 277,
                forkCount = 62,
                imageUrl = "https://picsum.photos/200?random=23"
            ),
            RepositoryVO(
                id = 24,
                name = "Pegasus",
                author = "Natália",
                starCount = 1345,
                forkCount = 289,
                imageUrl = "https://picsum.photos/200?random=24"
            ),
            RepositoryVO(
                id = 25,
                name = "Hydra",
                author = "Marcelo",
                starCount = 498,
                forkCount = 112,
                imageUrl = "https://picsum.photos/200?random=25"
            ),
            RepositoryVO(
                id = 26,
                name = "Comet",
                author = "Sabrina",
                starCount = 722,
                forkCount = 156,
                imageUrl = "https://picsum.photos/200?random=26"
            ),
            RepositoryVO(
                id = 27,
                name = "Eos",
                author = "Diego",
                starCount = 943,
                forkCount = 201,
                imageUrl = "https://picsum.photos/200?random=27"
            ),
            RepositoryVO(
                id = 28,
                name = "Auriga",
                author = "Tatiane",
                starCount = 315,
                forkCount = 67,
                imageUrl = "https://picsum.photos/200?random=28"
            ),
            RepositoryVO(
                id = 29,
                name = "Solaris",
                author = "Ricardo",
                starCount = 1176,
                forkCount = 254,
                imageUrl = "https://picsum.photos/200?random=29"
            ),
            RepositoryVO(
                id = 30,
                name = "Vega",
                author = "Monica",
                starCount = 456,
                forkCount = 98,
                imageUrl = "https://picsum.photos/200?random=30"
            ),
            RepositoryVO(
                id = 31,
                name = "NebulaCore",
                author = "Henrique",
                starCount = 1899,
                forkCount = 399,
                imageUrl = "https://picsum.photos/200?random=31"
            ),
            RepositoryVO(
                id = 32,
                name = "Pulsar",
                author = "Clara",
                starCount = 603,
                forkCount = 122,
                imageUrl = "https://picsum.photos/200?random=32"
            ),
            RepositoryVO(
                id = 33,
                name = "Galileo",
                author = "Roberto",
                starCount = 268,
                forkCount = 51,
                imageUrl = "https://picsum.photos/200?random=33"
            ),
            RepositoryVO(
                id = 34,
                name = "Apollo",
                author = "Sofia",
                starCount = 1390,
                forkCount = 287,
                imageUrl = "https://picsum.photos/200?random=34"
            ),
            RepositoryVO(
                id = 35,
                name = "Minerva",
                author = "Leonardo",
                starCount = 492,
                forkCount = 113,
                imageUrl = "https://picsum.photos/200?random=35"
            ),
            RepositoryVO(
                id = 36,
                name = "Janus",
                author = "Carolina",
                starCount = 764,
                forkCount = 154,
                imageUrl = "https://picsum.photos/200?random=36"
            ),
            RepositoryVO(
                id = 37,
                name = "AtlasX",
                author = "Bruno",
                starCount = 341,
                forkCount = 72,
                imageUrl = "https://picsum.photos/200?random=37"
            ),
            RepositoryVO(
                id = 38,
                name = "Chronos",
                author = "Paula",
                starCount = 1725,
                forkCount = 365,
                imageUrl = "https://picsum.photos/200?random=38"
            ),
            RepositoryVO(
                id = 39,
                name = "Titan",
                author = "Alexandre",
                starCount = 827,
                forkCount = 176,
                imageUrl = "https://picsum.photos/200?random=39"
            ),
            RepositoryVO(
                id = 40,
                name = "Selene",
                author = "Mariana",
                starCount = 198,
                forkCount = 45,
                imageUrl = "https://picsum.photos/200?random=40"
            ),
            RepositoryVO(
                id = 41,
                name = "Gaia",
                author = "Rodrigo",
                starCount = 1367,
                forkCount = 295,
                imageUrl = "https://picsum.photos/200?random=41"
            ),
            RepositoryVO(
                id = 42,
                name = "Oceanus",
                author = "Bianca",
                starCount = 474,
                forkCount = 109,
                imageUrl = "https://picsum.photos/200?random=42"
            ),
            RepositoryVO(
                id = 43,
                name = "Icarus",
                author = "Fábio",
                starCount = 1098,
                forkCount = 243,
                imageUrl = "https://picsum.photos/200?random=43"
            ),
            RepositoryVO(
                id = 44,
                name = "Nyx",
                author = "Daniela",
                starCount = 212,
                forkCount = 52,
                imageUrl = "https://picsum.photos/200?random=44"
            ),
            RepositoryVO(
                id = 45,
                name = "Chronicle",
                author = "Vitor",
                starCount = 981,
                forkCount = 207,
                imageUrl = "https://picsum.photos/200?random=45"
            ),
            RepositoryVO(
                id = 46,
                name = "Erebus",
                author = "Simone",
                starCount = 653,
                forkCount = 133,
                imageUrl = "https://picsum.photos/200?random=46"
            ),
            RepositoryVO(
                id = 47,
                name = "Prometheus",
                author = "Luiz",
                starCount = 742,
                forkCount = 165,
                imageUrl = "https://picsum.photos/200?random=47"
            ),
            RepositoryVO(
                id = 48,
                name = "Zephyr",
                author = "Catarina",
                starCount = 889,
                forkCount = 179,
                imageUrl = "https://picsum.photos/200?random=48"
            ),
            RepositoryVO(
                id = 49,
                name = "Oberon",
                author = "Rafaela",
                starCount = 316,
                forkCount = 64,
                imageUrl = "https://picsum.photos/200?random=49"
            ),
            RepositoryVO(
                id = 50,
                name = "Hades",
                author = "Fernando",
                starCount = 1594,
                forkCount = 327,
                imageUrl = "https://picsum.photos/200?random=50"
            ),
        )
    }
}
