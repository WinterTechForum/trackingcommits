package wintertechforum.trackingcommits.k8s

import io.kubernetes.client.apis.AppsV1Api
import io.kubernetes.client.util.Config


class KubernetesPoller(
    private val config: KubernetesClusterConfiguration
) {
  private val client = Config.defaultClient()

  fun listDeployments(): List<KubernetesServerGroup> = AppsV1Api(client).listNamespacedDeployment(
      config.namespace,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
  ).items.map {
    KubernetesServerGroup(
        config,
        KubernetesServerGroup.ServerGroupType.DEPLOYMENT,
        it.metadata.name,
        it.metadata.labels ?: emptyMap(),
        it.spec.selector.matchLabels,
        it.metadata.generation
    )
  }

  fun listStatefulSets(): List<KubernetesServerGroup> = AppsV1Api(client).listNamespacedStatefulSet(
      config.namespace,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
  ).items.map {
    KubernetesServerGroup(
        config,
        KubernetesServerGroup.ServerGroupType.STATEFUL_SET,
        it.metadata.name,
        it.metadata.labels ?: emptyMap(),
        it.spec.selector.matchLabels,
        it.metadata.generation
    )
  }
}

// Make this main to play with the kubernetes poller and resolver
fun notMain() {
  val config = KubernetesClusterConfiguration(
      "minikube",
      "default"
  )
  val poller = KubernetesPoller(
      config
  )
  val resolver = KubernetesImageResolver(
      config
  )
  (poller.listDeployments() + poller.listStatefulSets()).forEach {
    println(it)
    resolver.listServerGroupImages(it).forEach {
      println("> $it")
    }
  }
}
