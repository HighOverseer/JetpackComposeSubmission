package com.example.jetpackcomposesubmission.model

import com.example.jetpackcomposesubmission.R

data class User(
    val userPreview: UserPreview,
    val followingCount: Int,
    val followerCount: Int,
    val repositoriesCount: Int,
    val companyName: String,
    val address: String,
    val repoPreviews: List<RepositoryPreview>,
    val isFavorite: Boolean = false
) {
    val firstName = userPreview.name.split(" ")[0]
}

fun getUsers(): List<User> {
    return listOf(
        User(
            userPreview = UserPreview(
                1,
                "@Markk",
                "Mark Zuheif",
                avatar = R.drawable.user_1
            ),
            100,
            44,
            10,
            "Meta",
            "Washington DC",
            generateRepositoryList(
                listOf(
                    "QuantumPanda",
                    "CyberSphinx",
                    "NebulaForge",
                    "CryptoHarmony",
                    "BioGizmo",
                    "SynthWaveAI",
                    "SolarFlareOS",
                    "AeroSwift",
                    "ZenithBot",
                    "BlazeNavigator"
                ),
                "@Markk"
            )
        ),
        User(
            userPreview = UserPreview(
                2,
                "@MikaelaFR",
                "Mikaela Frans",
                avatar = R.drawable.user_2
            ),
            102,
            221,
            10,
            "Amazon",
            "London",
            generateRepositoryList(
                listOf(
                    "QuantumOrbit",
                    "CodeSpectra",
                    "ByteNebula",
                    "TechPulseHub",
                    "BioGeniusLab",
                    "DataHarborX",
                    "AeroCraftPro",
                    "ChronoEngine",
                    "LunaSyncSystem",
                    "EchoCipherSpace"
                ),
                "@MikaelaFR"
            )
        ),
        User(
            userPreview = UserPreview(
                3,
                "@rgosling",
                "Ryan Gosling",
                avatar = R.drawable.user_3
            ),
            212,
            444,
            10,
            "Google",
            "West Virginia",
            generateRepositoryList(
                listOf(
                    "QuantumPulse",
                    "CodeFusion",
                    "ByteDynamics",
                    "TechInnovateHub",
                    "BioCatalystLab",
                    "DataNebulaExplorer",
                    "AeroNauticCraft",
                    "ChronoTechEngine",
                    "LunaSyncGateway",
                    "EchoCipherSystem"
                ),
                "@rgosling"
            )
        ),
        User(
            userPreview = UserPreview(
                4,
                "@PeterP",
                "Peter Parker",
                avatar = R.drawable.user_4
            ),
            11,
            21,
            14,
            "Techno Media",
            "Amsterdam",
            generateRepositoryList(
                listOf(
                    "NovaSpark",
                    "CodeInfiniteX",
                    "ByteQuasar",
                    "TechNebulaCore",
                    "BioCatalystGenesis",
                    "DataVortexExplorer",
                    "AeroNauticVoyager",
                    "ChronoInnovateCraft",
                    "LunaSyncNavigator",
                    "EchoCipherDynamo",
                    "GalacticPulse",
                    "InfraCodeX",
                    "QuasarByte",
                    "NebulaTechHub"
                ),
                "@PeterP"
            )
        ),
        User(
            userPreview = UserPreview(
                5,
                "@MelindaL",
                "Melinda Lauren",
                avatar = R.drawable.user_5
            ),
            302,
            33,
            12,
            "Huawei Corp",
            "Shanghai",
            generateRepositoryList(
                listOf(
                    "AstroByte",
                    "CodeSpectacle",
                    "QuantumPulseX",
                    "TechNebulaSprint",
                    "BioCatalystInno",
                    "DataVortexVoyage",
                    "AeroNauticGalaxy",
                    "ChronoCraftElite",
                    "LunaSyncPioneer",
                    "EchoCipherSpark",
                    "StellarQuasar",
                    "InfiniteCodeX"
                ),
                "@MelindaL"
            )
        ),
        User(
            userPreview = UserPreview(
                6,
                "@SamSep",
                "Sam Sepiol",
                avatar = R.drawable.user_6
            ),
            43,
            332,
            12,
            "E Corp",
            "Ohio",
            generateRepositoryList(
                listOf(
                    "GalacticPulseX",
                    "CodeNovaSphere",
                    "NebulaQuantumX",
                    "TechVortexSprint",
                    "BioInfiniteCatalyst",
                    "DataNavigatorVortex",
                    "AeroCraftGalaxy",
                    "ChronoEliteCraft",
                    "LunaInnovateSync",
                    "EchoSparkCipher",
                    "StellarQuasarX",
                    "InfiniteTechCode"
                ),
                "@SamSep"
            )
        ),
        User(
            userPreview = UserPreview(
                7,
                "@GraceER",
                "Grace Enro",
                avatar = R.drawable.user_7
            ),
            54,
            253,
            16,
            "Rockstar",
            "Oregon",
            generateRepositoryList(
                listOf(
                    "NebulaSpectraX",
                    "CodeQuantumXplorer",
                    "StellarPulseInno",
                    "TechVoyagerSprint",
                    "BioInfiniteForge",
                    "DataNavigatorVortexX",
                    "AeroQuantumGalactic",
                    "ChronoEliteInnovator",
                    "LunaSyncPioneerX",
                    "EchoCipherSparkX",
                    "GalacticQuasarInno",
                    "InfiniteTechNavigator",
                    "QuasarByteInnovate",
                    "NovaCraftXplorer",
                    "SpectraPulseGalaxy",
                    "QuantumInnoXplorer"
                ),
                "@GraceER"
            )
        ),
        User(
            userPreview = UserPreview(
                8,
                "@SonyaMO",
                "Sonya Moore",
                avatar = R.drawable.user_8
            ),
            243,
            255,
            13,
            "All Safe Corp",
            "North Carolina",
            generateRepositoryList(
                listOf(
                    "StellarPulseExplorer",
                    "CodeQuantumVoyage",
                    "NebulaInfiniteSprint",
                    "TechVortexElite",
                    "BioGalacticForge",
                    "DataSpectraNavigator",
                    "AeroQuasarNavigator",
                    "ChronoTechInnovate",
                    "LunaCipherPioneer",
                    "EchoInnoSpark",
                    "QuasarEliteXplorer",
                    "InfiniteNovaCraft",
                    "VoyagerGalacticX"
                ),
                "@SonyaMO"
            )
        ),
        User(
            userPreview = UserPreview(
                9,
                "@SandyMn",
                "Sandy Maine",
                avatar = R.drawable.user_9
            ),
            2,
            109,
            14,
            "Sentrion",
            "Hongkong",
            generateRepositoryList(
                listOf(
                    "QuantumVoyageX",
                    "CodeSpectraExplorer",
                    "NebulaPulseInnovate",
                    "TechInfiniteNavigator",
                    "BioGalacticForgeX",
                    "DataQuantumPioneer",
                    "AeroInnovatorCraft",
                    "ChronoVortexSprint",
                    "LunaCipherElite",
                    "EchoNavigatorSpark",
                    "QuasarTechXplorer",
                    "InfiniteNovaNavigator",
                    "VoyagerSpectraCraft",
                    "PulseQuantumExplorer"
                ),
                "@SandyMn"
            )
        ),
        User(
            userPreview = UserPreview(
                10,
                "@Fikiki",
                "Fiki Naki",
                avatar = R.drawable.user_10
            ),
            90,
            143,
            15,
            "GoTo",
            "Jakarta",
            generateRepositoryList(
                listOf(
                    "SpectraCraftVoyage",
                    "CodeInfiniteXplorer",
                    "QuantumPioneerElite",
                    "TechNavigatorSprint",
                    "BioInnovateGalaxy",
                    "DataVortexQuantum",
                    "AeroPulseNavigator",
                    "ChronoEliteTechCraft",
                    "LunaSparkCipher",
                    "EchoQuasarInnovator",
                    "NovaVortexXplorer",
                    "InfiniteCraftNova",
                    "VoyageGalacticTech",
                    "SpectraEliteNavigator",
                    "QuantumPulseXplorer"
                ),
                "@Fikiki"
            )
        ),
    )
}




