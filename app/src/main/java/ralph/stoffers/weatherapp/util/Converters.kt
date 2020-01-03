package ralph.stoffers.weatherapp.util

class Converters {
    companion object {
        fun convertDegreeToCardinalDirection(directionInDegrees: Int): String {
            val cardinalDirection: String?
            if (directionInDegrees >= 348.75 && directionInDegrees <= 360 || directionInDegrees >= 0 && directionInDegrees <= 11.25) {
                cardinalDirection = "N"
            } else if (directionInDegrees >= 11.25 && directionInDegrees <= 33.75) {
                cardinalDirection = "NNE"
            } else if (directionInDegrees >= 33.75 && directionInDegrees <= 56.25) {
                cardinalDirection = "NE"
            } else if (directionInDegrees >= 56.25 && directionInDegrees <= 78.75) {
                cardinalDirection = "ENE"
            } else if (directionInDegrees >= 78.75 && directionInDegrees <= 101.25) {
                cardinalDirection = "E"
            } else if (directionInDegrees >= 101.25 && directionInDegrees <= 123.75) {
                cardinalDirection = "ESE"
            } else if (directionInDegrees >= 123.75 && directionInDegrees <= 146.25) {
                cardinalDirection = "SE"
            } else if (directionInDegrees >= 146.25 && directionInDegrees <= 168.75) {
                cardinalDirection = "SSE"
            } else if (directionInDegrees >= 168.75 && directionInDegrees <= 191.25) {
                cardinalDirection = "S"
            } else if (directionInDegrees >= 191.25 && directionInDegrees <= 213.75) {
                cardinalDirection = "SSW"
            } else if (directionInDegrees >= 213.75 && directionInDegrees <= 236.25) {
                cardinalDirection = "SW"
            } else if (directionInDegrees >= 236.25 && directionInDegrees <= 258.75) {
                cardinalDirection = "WSW"
            } else if (directionInDegrees >= 258.75 && directionInDegrees <= 281.25) {
                cardinalDirection = "W"
            } else if (directionInDegrees >= 281.25 && directionInDegrees <= 303.75) {
                cardinalDirection = "WNW"
            } else if (directionInDegrees >= 303.75 && directionInDegrees <= 326.25) {
                cardinalDirection = "NW"
            } else if (directionInDegrees >= 326.25 && directionInDegrees <= 348.75) {
                cardinalDirection = "NNW"
            } else {
                cardinalDirection = "?"
            }

            return cardinalDirection
        }

        fun convertMetersPerSecondToBft(speed: Double): Int {
            return when {
                speed <= 0.2 -> 0
                speed <= 1.5 -> 1
                speed <= 3.3 -> 2
                speed <= 5.4 -> 3
                speed <= 7.9 -> 4
                speed <= 10.7 -> 5
                speed <= 13.8 -> 6
                speed <= 17.1 -> 7
                speed <= 20.7 -> 8
                speed <= 24.4 -> 9
                speed <= 28.4 -> 10
                speed <= 32.6 -> 11
                else -> 12
            }
        }
    }
}