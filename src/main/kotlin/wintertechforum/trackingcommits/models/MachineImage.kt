package wintertechforum.trackingcommits.models

import javax.persistence.*

@Entity
data class MachineImage (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        @Column(nullable = false)
        val provider: MachineImageProvider,

        @Column(nullable = false)
        val providerId: String,

        @Column(nullable = false)
        val providerScope: String
)

enum class MachineImageProvider {
    DOCKER, AMAZON
}