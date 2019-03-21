package wintertechforum.trackingcommits.k8s

data class KubernetesServerGroup(
    val config: KubernetesClusterConfiguration,
    val resourceType: ServerGroupType,
    val resourceName: String,
    val resourceLabels: Map<String, String>,
    val selectorMatchLabels: Map<String, String>,
    val generation: Long
){
  enum class ServerGroupType {
    DEPLOYMENT, STATEFUL_SET
  }
}
