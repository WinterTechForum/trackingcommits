package wintertechforum.trackingcommits.k8s

data class KubernetesClusterConfiguration(
    val clusterName: String,
    val namespace: String
)
