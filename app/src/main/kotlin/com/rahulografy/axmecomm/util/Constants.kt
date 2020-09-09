package com.rahulografy.axmecomm.util

object Constants {

    object Network {

        /*object Api {

            const val URL_BASE = "https://api.jsonbin.io/b/5f3a3fcf4d939910361666fe/"

            const val URL_GET_PRODUCTS = "latest"

            const val HEADER_SECRET_KEY_KEY = "secret-key"

            // TODO | MOVE THIS TO MORE SECURE PLACE
            const val HEADER_SECRET_KEY_VALUE =
                "\$2b\$10\$ldwbG.B/2hNRvS2dzXDxoO5P87sYGwoE02SliZIh.8vlvsSctGqF2"
        }*/

        // TODO | MY PERSONAL MOCK API AS Axm's JSONBIN IS DOWN
        object Api {

            const val URL_BASE = "https://run.mocky.io/v3/ceaf40f8-0a4b-4eaf-b368-bd356bc64523/"

            const val URL_GET_PRODUCTS = " "

            const val HEADER_SECRET_KEY_KEY = "secret-key"

            // TODO | MOVE THIS TO MORE SECURE PLACE
            const val HEADER_SECRET_KEY_VALUE =
                "\$2b\$10\$sjrWacntxXez1W6L4QMwvOfQ9Wrsgj5MiqMZ/gBd77QoURPglBPr."
        }

        object Timeout {

            const val CONNECTION = 10L

            const val WRITE = 10L

            const val READ = 30L
        }

        object Cache {

            const val NAME = "AxmEcommCache"
        }
    }
}