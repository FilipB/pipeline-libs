package com.redhat.qe.kiali.pipeline.stage

class Stages {
    static def deployRegistryPuller(script) {
        script.sh "cat src/jenkinsfiles/templates/olm/custom/namespace.yaml | NAMESPACE=registry-puller envsubst | oc apply -f -"
        script.sh "oc create configmap -n registry-puller registry-secret --from-file=src/jenkinsfiles/templates/olm/custom/secret.yaml"
        script.sh "oc apply -f https://raw.githubusercontent.com/knrc/registry-puller/master/registry-puller-4.0.yaml"
    }
    
    static def cleanEnv(script) {
        script.build(job: 'clean-environment',
            parameters: [
              [$class: 'StringParameterValue', value: script.params.OCP_CRED_ID, name: 'OCP_CRED_ID'],
              [$class: 'StringParameterValue', value: script.params.OS_HOSTNAME, name: 'OS_HOSTNAME'],
              [$class: 'StringParameterValue', value: script.params.OS_PORT, name: 'OS_PORT'],
              [$class: 'hudson.model.BooleanParameterValue', value: true, name: 'DELETE_OLM_SUBSCRIPTIONS'],
              [$class: 'hudson.model.BooleanParameterValue', value: true, name: 'DELETE_MAISTRA_CONTROL_PLANES'],
              [$class: 'hudson.model.BooleanParameterValue', value: true, name: 'DELETE_MAISTRA_OPERATOR'],
              [$class: 'hudson.model.BooleanParameterValue', value: true, name: 'DELETE_JAEGER_OPERATOR'],
              [$class: 'hudson.model.BooleanParameterValue', value: true, name: 'DELETE_ISTIO'],
              [$class: 'hudson.model.BooleanParameterValue', value: true, name: 'DELETE_KIALI_CR'],
              [$class: 'hudson.model.BooleanParameterValue', value: true, name: 'DELETE_KIALI_OPERATOR']
           ]
        )
    }
    
}
