.PHONY: build deploy destroy invoke

STACK=project2
FUNCTION_NAME=project2
BUCKET=lambda-function-repository-project2

build:
	mvn clean package

deploy:
	aws cloudformation package --template-file template.yml --s3-bucket $(BUCKET) --output-template-file out.yml
	aws cloudformation deploy --template-file out.yml --stack-name $(STACK) --capabilities CAPABILITY_NAMED_IAM

destroy:
	aws cloudformation delete-stack --stack-name $(STACK)

invoke:
	aws lambda invoke --function-name $(FUNCTION_NAME) --log-type Tail out.json
