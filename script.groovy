def buildApp () {
  echo 'building the application'
}

def testApp () {
  echo 'testing the application'
}

def deploydApp () {
  echo 'deploying the application'
  echo "deploying version ${params.VERSION}"
}

return this
