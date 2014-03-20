require 'watir-webdriver'
require 'headless'

usage_msg = "Usage: update_jenkins_ami_headless.rb username password ami_value ci_url"

if ARGV.length != 4 then
	puts "failed - #{usage_msg}"
	exit -1
end

username = ARGV[0]
password = ARGV[1]
ami_value = ARGV[2]
ci_url = ARGV[3]

puts "\n"
puts "#{username}  #{ami_value}  #{ci_url}"
puts "\n"

puts "starting headless"
headless = Headless.new #:destroy_at_exit => false
headless.start


puts "launching web browser"
@browser = Watir::Browser.new

#@browser.goto("http://beta.ci.kuali.org")
puts "going to #{ci_url}"
@browser.goto(ci_url)

@browser.link(:text => "log in").wait_until_present
puts "log in link detected"
@browser.link(:text => "log in").click
 

@browser.text_field(:id => "username").wait_until_present
puts "logging in"
@browser.text_field(:id => "username").set username
@browser.text_field(:id => "password").set password
@browser.button(:name => "submit").click

@browser.link(:text => "Manage Jenkins").wait_until_present
puts "manage jenkins"
@browser.link(:text => "Manage Jenkins").click
@browser.td(:id => "main-panel").h1(:text => "Manage Jenkins").wait_until_present

puts "configure system"
@browser.table(:id => "management-links").link(:text => "Configure System").click
@browser.text_field(:name => "_.ami").wait_until_present

puts @browser.text_field(:name => "_.ami").value
puts "update ami -> #{ami_value}"

@browser.text_field(:name => "_.ami").set ami_value

# This is the id for the "Save" button
#@browser.button(:id => "yui-gen76-button").click

puts "save configuration"
@browser.button(:value => "Save").click

puts "log out"
@browser.link(:text => "log out").click
