package wintertechforum.trackingcommits.k8s

import io.kubernetes.client.apis.CoreV1Api
import io.kubernetes.client.util.Config
import wintertechforum.trackingcommits.docker.DockerImageSpec

class KubernetesImageResolver(
    val config: KubernetesClusterConfiguration
) {
  private val client = Config.defaultClient()

  fun listServerGroupImages(serverGroup: KubernetesServerGroup) =
      CoreV1Api(client).listNamespacedPod(
          config.namespace,
          null,
          null,
          null,
          null,
          serverGroup.selectorMatchLabels.map { (key, value) -> "$key=$value" }.joinToString(","),
          null,
          null,
          null,
          null
      ).items.flatMap { it.status.containerStatuses }.map {
        DockerImageSpec(
            it.image,
            it.imageID
        )
      }.distinct()
}

