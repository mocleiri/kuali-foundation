#!/bin/bash
#
# Copyright 2004-2014 The Kuali Foundation
#
# Licensed under the Educational Community License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.opensource.org/licenses/ecl2.php
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

check_not_blank GPG_PASSPHRASE $GPG_PASSPHRASE

ACCESS_KEY=jA0ECQMC9tn57Ux7OeK+0kkBxn8AuzIOHde/VXElmijVcG7f/WDBkjkHILYaCQ5UCwNdz5t3rzW1QVkKQoLBZI7P9IX8dNSvQAuiT8hlG61q2cGLMGPphIb1
SECRET_KEY=jA0ECQMCFwOPCX40T7O+0l0BE7n+7e6UyJFeYzBA4o7AJC2wnUOy8818WYwKF1xSZP1SnNfIQwiBuld5bvtcln75QzsBSaab+VsiLIR3LC4KO7l30HCVnCXr0AxBe6H6K/C0MaqXXxA0NF0BhZE=

ACCESS_KEY=$(decrypt $ACCESS_KEY $GPG_PASSPHRASE)
SECRET_KEY=$(decrypt $SECRET_KEY $GPG_PASSPHRASE)
